import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';

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

const MotherboardFormModal = ({ open, onClose, onSave, motherboardId, initialData }) => {
  const [memorySlots, setMemorySlots] = useState(0);
  const [chipset, setChipset] = useState('');
  const [formFactor, setFormFactor] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    if (initialData) {
      setMemorySlots(initialData.memorySlots || null);
      setChipset(initialData.chipset || null);
      setFormFactor(initialData.formFactor || null);
    } else {
      setMemorySlots(null);
      setChipset(null);
      setFormFactor(null);
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!memorySlots || !chipset || !formFactor) {
      setError('All fields are required');
      return;
    }

    const motherboardData = {
      memorySlots: parseInt(memorySlots, 10),
      chipset,
      formFactor,
    };
    onSave(motherboardData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {motherboardId ? 'Edit Motherboard' : 'Add Motherboard'}
        </Typography>
        {error && <Typography color="error">{error}</Typography>}
        <form onSubmit={handleSubmit}>
          <TextField
            label="Memory Slots"
            type="number"
            value={memorySlots}
            onChange={(e) => setMemorySlots(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Chipset"
            value={chipset}
            onChange={(e) => setChipset(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Form Factor"
            value={formFactor}
            onChange={(e) => setFormFactor(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {motherboardId ? 'Save Changes' : 'Add Motherboard'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default MotherboardFormModal;