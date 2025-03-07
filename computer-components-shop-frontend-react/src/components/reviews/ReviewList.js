import React, { useEffect, useState } from 'react';
import { getReviews, deleteReview, updateReview, createReview, getClients, getProducts } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import ReviewFormModal from './ReviewFormModal';

const ReviewList = () => {
  const [reviews, setReviews] = useState([]);
  const [clients, setClients] = useState([]);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingReviewId, setEditingReviewId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [reviewRes, clientRes, productRes] = await Promise.all([getReviews(), getClients(), getProducts()]);
        setReviews(reviewRes.data);
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
      await deleteReview(id);
      setReviews(reviews.filter((review) => review.id !== id));
    } catch (err) {
      console.error('Failed to delete review:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingReviewId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingReviewId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (reviewData) => {
    try {
      if (editingReviewId) {
        await updateReview(editingReviewId, reviewData);
      } else {
        const response = await createReview(reviewData);
        setReviews([...reviews, response.data]);
      }
      setIsModalOpen(false);
      const response = await getReviews();
      setReviews(response.data);
    } catch (err) {
      console.error('Failed to save review:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingReviewId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Reviews</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Review
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Client</TableCell>
              <TableCell>Product</TableCell>
              <TableCell>Review</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {reviews.map((review) => (
              <TableRow key={review.id}>
                <TableCell>{review.id}</TableCell>
                <TableCell>{review.client?.name}</TableCell>
                <TableCell>{review.product?.name}</TableCell>
                <TableCell>{review.review}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(review.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(review.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <ReviewFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        reviewId={editingReviewId}
        initialData={reviews.find((review) => review.id === editingReviewId)}
        clients={clients}
        products={products}
      />
    </div>
  );
};

export default ReviewList;
