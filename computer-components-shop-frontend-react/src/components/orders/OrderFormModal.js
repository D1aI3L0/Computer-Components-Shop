import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button, MenuItem, Select, FormControl, InputLabel, OutlinedInput, Chip } from '@mui/material';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 4,
  borderRadius: 2,
};

const OrderFormModal = ({ open, onClose, onSave, orderId, initialData, clients, products }) => {
  const [orderDate, setOrderDate] = useState(initialData?.orderDate || '');
  const [status, setStatus] = useState(initialData?.status || '');
  const [paymentMethod, setPaymentMethod] = useState(initialData?.paymentMethod || '');
  const [selectedClient, setSelectedClient] = useState(initialData?.client?.id || '');
  const [selectedProducts, setSelectedProducts] = useState(initialData?.products?.map(p => p.id) || []);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    if (initialData) {
      setOrderDate(initialData.orderDate || '');
      setStatus(initialData.status || '');
      setPaymentMethod(initialData.paymentMethod || '');
      setSelectedClient(initialData.client?.id || '');
      setSelectedProducts(initialData.products?.map(p => p.id) || []);
    }
  }, [initialData]);

  useEffect(() => {
    const price = selectedProducts.reduce((sum, productId) => {
      const product = products.find(p => p.id === productId);
      return sum + (product ? product.price : 0);
    }, 0);
    setTotalPrice(price);
  }, [selectedProducts, products]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const orderData = { 
      totalPrice, 
      orderDate, 
      status, 
      paymentMethod, 
      client: clients.find(c => c.id === selectedClient), 
      products: products.filter(p => selectedProducts.includes(p.id)) 
    };
    onSave(orderData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {orderId ? 'Edit Order' : 'Add Order'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField label="Total Price" type="number" value={totalPrice} fullWidth margin="normal" InputProps={{ readOnly: true }} />
          <TextField label="Order Date" type="date" value={orderDate} onChange={(e) => setOrderDate(e.target.value)} fullWidth margin="normal" required InputLabelProps={{ shrink: true }} />
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Status</InputLabel>
            <Select value={status} onChange={(e) => setStatus(e.target.value)}>
              <MenuItem value="Waiting for confirmation">Waiting for confirmation</MenuItem>
              <MenuItem value="In transit">In transit</MenuItem>
              <MenuItem value="Expectation">Expectation</MenuItem>
              <MenuItem value="Received">Received</MenuItem>
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Payment method</InputLabel>
            <Select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
              <MenuItem value="PayPal">PayPal</MenuItem>
              <MenuItem value="Credit card">Credit card</MenuItem>
              <MenuItem value="Cash">Cash</MenuItem>
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Client</InputLabel>
            <Select value={selectedClient} onChange={(e) => setSelectedClient(e.target.value)}>
              {clients.map((client) => (
                <MenuItem key={client.id} value={client.id}>{client.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Products</InputLabel>
            <Select multiple value={selectedProducts} onChange={(e) => setSelectedProducts(e.target.value)} input={<OutlinedInput label="Products" />} renderValue={(selected) => (
              <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                {selected.map((productId) => {
                  const product = products.find(p => p.id === productId);
                  return product ? <Chip key={product.id} label={product.name} /> : null;
                })}
              </Box>
            )}>
              {products.map((product) => (
                <MenuItem key={product.id} value={product.id}>{product.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {orderId ? 'Save Changes' : 'Add Order'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default OrderFormModal;
