import React, { useEffect, useState } from 'react';
import { getProcessors, deleteProcessor, createProcessor, updateProcessor } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import ProcessorFormModal from './ProcessorFormModal';

const ProcessorList = () => {
  const [processors, setProcessors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingProcessorId, setEditingProcessorId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchProcessors = async () => {
      try {
        const response = await getProcessors();
        setProcessors(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProcessors();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteProcessor(id);
      setProcessors(processors.filter((processor) => processor.id !== id));
    } catch (err) {
      console.error('Failed to delete processor:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingProcessorId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingProcessorId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (processorData) => {
    try {
      if (editingProcessorId) {
        await updateProcessor(editingProcessorId, processorData);
      } else {
        const response = await createProcessor(processorData);
        setProcessors([...processors, response.data]);
      }
      setIsModalOpen(false);

      const response = await getProcessors();
      setProcessors(response.data);
    } catch (err) {
      console.error('Failed to save processor:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingProcessorId(null);
  };

  const formatFloat = (num) => {
    return num ? num.toFixed(2) : '0.00';
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Processors</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Processor
      </Button>
      <TableContainer component={Paper} sx={{ mt: 2 }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Threads Count</TableCell>
              <TableCell>Clock Frequency (GHz)</TableCell>
              <TableCell>Max Frequency (GHz)</TableCell>
              <TableCell>CPU Count</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {processors.map((processor) => (
              <TableRow key={processor.id}>
                <TableCell>{processor.threadsCount}</TableCell>
                <TableCell>{formatFloat(processor.clockFrequency)}</TableCell>
                <TableCell>{formatFloat(processor.maxFrequency)}</TableCell>
                <TableCell>{processor.cpuCount}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(processor.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(processor.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <ProcessorFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        processorId={editingProcessorId}
        initialData={processors.find((processor) => processor.id === editingProcessorId)}
      />
    </div>
  );
};

export default ProcessorList;