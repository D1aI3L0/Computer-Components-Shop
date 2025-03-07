import React, { useEffect, useState } from 'react';
import { getProducts, deleteProduct, updateProduct, createProduct } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import ProductFormModal from './ProductFormModal';
import { formatFloat } from '../FloatFormater';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingProductId, setEditingProductId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await getProducts();
        setProducts(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteProduct(id);
      setProducts(products.filter((product) => product.id !== id));
    } catch (err) {
      console.error('Failed to delete product:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingProductId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingProductId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (productData) => {
    try {
      if (editingProductId) {
        await updateProduct(editingProductId, productData);
      } else {
        const response = await createProduct(productData);
        setProducts([...products, response.data]);
      }
      setIsModalOpen(false);
      const response = await getProducts();
      setProducts(response.data);
    } catch (err) {
      console.error('Failed to save product:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingProductId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Products</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Product
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Price</TableCell>
              <TableCell>Manufacturer</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Processor</TableCell>
              <TableCell>Motherboard</TableCell>
              <TableCell>Graphic Card</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.map((product) => (
              <TableRow key={product.id}>
                <TableCell>{product.name}</TableCell>
                <TableCell>{formatFloat(product.price)}</TableCell>
                <TableCell>{product.manufacturer}</TableCell>
                <TableCell>{product.type}</TableCell>
                <TableCell>
                  {product.processor ? `${product.processor.cpuCount}CPUs, ${formatFloat(product.processor.clockFrequency)}GHz` : 'N/A'}
                </TableCell>
                <TableCell>
                  {product.motherboard ? `${product.motherboard.memorySlots} mem slots, ${product.motherboard.chipset}` : 'N/A'}
                </TableCell>
                <TableCell>
                  {product.graphicCard ? `${product.graphicCard.gpuCount} GPUs, ${product.graphicCard.memoryCount}GB VRAM` : 'N/A'}
                </TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(product.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(product.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <ProductFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        productId={editingProductId}
        initialData={products.find((product) => product.id === editingProductId)}
      />
    </div>
  );
};

export default ProductList;
