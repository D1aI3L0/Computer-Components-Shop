import React, { useEffect, useState } from 'react';
import { getOrders, deleteOrder, updateOrder, createOrder, getClients, getProducts } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import OrderFormModal from './OrderFormModal';

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const [clients, setClients] = useState([]);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingOrderId, setEditingOrderId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [orderRes, clientRes, productRes] = await Promise.all([getOrders(), getClients(), getProducts()]);
        setOrders(orderRes.data);
        setClients(clientRes.data);
        setProducts(productRes.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteOrder(id);
      setOrders(orders.filter((order) => order.id !== id));
    } catch (err) {
      console.error('Failed to delete order:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingOrderId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingOrderId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (orderData) => {
    try {
      if (editingOrderId) {
        await updateOrder(editingOrderId, orderData);
      } else {
        const response = await createOrder(orderData);
        setOrders([...orders, response.data]);
      }
      setIsModalOpen(false);
      const response = await getOrders();
      setOrders(response.data);
    } catch (err) {
      console.error('Failed to save order:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingOrderId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Orders</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Order
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Total Price</TableCell>
              <TableCell>Order Date</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Payment Method</TableCell>
              <TableCell>Client</TableCell>
              <TableCell>Products</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {orders.map((order) => (
              <TableRow key={order.id}>
                <TableCell>{order.id}</TableCell>
                <TableCell>{order.totalPrice+'$'}</TableCell>
                <TableCell>{new Date(order.orderDate).toLocaleDateString()}</TableCell>
                <TableCell>{order.status}</TableCell>
                <TableCell>{order.paymentMethod}</TableCell>
                <TableCell>{order.client?.name}</TableCell>
                <TableCell>{order.products.map(p => <div>{p.name + ':' + p.price + "$"}</div>)}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(order.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(order.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <OrderFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        orderId={editingOrderId}
        initialData={orders.find((order) => order.id === editingOrderId)}
        clients={clients}
        products={products}
      />
    </div>
  );
};

export default OrderList;
