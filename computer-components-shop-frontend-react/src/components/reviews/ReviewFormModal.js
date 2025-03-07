import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button, MenuItem, Select, FormControl, InputLabel } from '@mui/material';

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

const ReviewFormModal = ({ open, onClose, onSave, reviewId, initialData, clients, products }) => {
  const [selectedClient, setSelectedClient] = useState(initialData?.client?.id || '');
  const [selectedProduct, setSelectedProduct] = useState(initialData?.product?.id || '');
  const [reviewText, setReviewText] = useState(initialData?.review || '');

  useEffect(() => {
    if (initialData) {
      setSelectedClient(initialData.client?.id || '');
      setSelectedProduct(initialData.product?.id || '');
      setReviewText(initialData.review || '');
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const reviewData = {
      client: clients.find(c => c.id === selectedClient),
      product: products.find(p => p.id === selectedProduct),
      review: reviewText,
    };
    console.log(reviewData);
    onSave(reviewData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {reviewId ? 'Edit Review' : 'Add Review'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Client</InputLabel>
            <Select value={selectedClient} onChange={(e) => setSelectedClient(e.target.value)}>
              {clients.map((client) => (
                <MenuItem key={client.id} value={client.id}>{client.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Product</InputLabel>
            <Select value={selectedProduct} onChange={(e) => setSelectedProduct(e.target.value)}>
              {products.map((product) => (
                <MenuItem key={product.id} value={product.id}>{product.name}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <TextField label="Review" multiline rows={4} value={reviewText} onChange={(e) => setReviewText(e.target.value)} fullWidth margin="normal" required />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {reviewId ? 'Save Changes' : 'Add Review'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default ReviewFormModal;