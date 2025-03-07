import React, { useEffect, useState } from 'react';
import { getGraphicCards, deleteGraphicCard, createGraphicCard, updateGraphicCard } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import GraphicCardFormModal from './GraphicCardFormModal';
import { formatFloat } from '../FloatFormater';

const GraphicCardList = () => {
  const [graphicCards, setGraphicCards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingGraphicCardId, setEditingGraphicCardId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchGraphicCards = async () => {
      try {
        const response = await getGraphicCards();
        setGraphicCards(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchGraphicCards();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteGraphicCard(id);
      setGraphicCards(graphicCards.filter((graphicCard) => graphicCard.id !== id));
    } catch (err) {
      console.error('Failed to delete graphic card:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingGraphicCardId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingGraphicCardId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (graphicCardData) => {
    try {
      if (editingGraphicCardId) {
        await updateGraphicCard(editingGraphicCardId, graphicCardData);
      } else {
        const response = await createGraphicCard(graphicCardData);
        setGraphicCards([...graphicCards, response.data]);
      }
      setIsModalOpen(false);

      const response = await getGraphicCards();
      setGraphicCards(response.data);
    } catch (err) {
      console.error('Failed to save graphic card:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingGraphicCardId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Graphic Cards</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Graphic Card
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>GPU Count</TableCell>
              <TableCell>GPU Frequency (GHz)</TableCell>
              <TableCell>Memory Count (GB)</TableCell>
              <TableCell>Memory Frequency (GHz)</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {graphicCards.map((graphicCard) => (
              <TableRow key={graphicCard.id}>
                <TableCell>{graphicCard.gpuCount}</TableCell>
                <TableCell>{formatFloat(graphicCard.gpuFrequency)}</TableCell>
                <TableCell>{graphicCard.memoryCount}</TableCell>
                <TableCell>{formatFloat(graphicCard.memoryFrequency)}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(graphicCard.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(graphicCard.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <GraphicCardFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        graphicCardId={editingGraphicCardId}
        initialData={graphicCards.find((graphicCard) => graphicCard.id === editingGraphicCardId)}
      />
    </div>
  );
};

export default GraphicCardList;