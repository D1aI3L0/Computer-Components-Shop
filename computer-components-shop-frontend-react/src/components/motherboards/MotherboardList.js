import React, { useEffect, useState } from 'react';
import { getMotherboards, deleteMotherboard, createMotherboard, updateMotherboard } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import MotherboardFormModal from './MotherboardFormModal';

const MotherboardList = () => {
  const [motherboards, setMotherboards] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingMotherboardId, setEditingMotherboardId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchMotherboards = async () => {
      try {
        const response = await getMotherboards();
        setMotherboards(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchMotherboards();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteMotherboard(id);
      setMotherboards(motherboards.filter((motherboard) => motherboard.id !== id));
    } catch (err) {
      console.error('Failed to delete motherboard:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingMotherboardId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingMotherboardId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (motherboardData) => {
    try {
      if (editingMotherboardId) {
        await updateMotherboard(editingMotherboardId, motherboardData);
      } else {
        const response = await createMotherboard(motherboardData);
        setMotherboards([...motherboards, response.data]);
      }
      setIsModalOpen(false);

      const response = await getMotherboards();
      setMotherboards(response.data);
    } catch (err) {
      console.error('Failed to save motherboard:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingMotherboardId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Motherboards</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Motherboard
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Memory Slots</TableCell>
              <TableCell>Chipset</TableCell>
              <TableCell>Form Factor</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {motherboards.map((motherboard) => (
              <TableRow key={motherboard.id}>
                <TableCell>{motherboard.memorySlots}</TableCell>
                <TableCell>{motherboard.chipset}</TableCell>
                <TableCell>{motherboard.formFactor}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(motherboard.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(motherboard.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <MotherboardFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        motherboardId={editingMotherboardId}
        initialData={motherboards.find((motherboard) => motherboard.id === editingMotherboardId)}
      />
    </div>
  );
};

export default MotherboardList;