import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';
import { formatFloat } from '../FloatFormater';

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

const GraphicCardFormModal = ({ open, onClose, onSave, graphicCardId, initialData }) => {
  const [gpuCount, setGpuCount] = useState('');
  const [gpuFrequency, setGpuFrequency] = useState('');
  const [memoryCount, setMemoryCount] = useState('');
  const [memoryFrequency, setMemoryFrequency] = useState('');
  const [error, setError] = useState(null);

  useEffect(() => {
    if (initialData) {
      setGpuCount(initialData.gpuCount || '');
      setGpuFrequency(formatFloat(initialData.gpuFrequency) || '');
      setMemoryCount(initialData.memoryCount || '');
      setMemoryFrequency(formatFloat(initialData.memoryFrequency) || '');
    } else {
      setGpuCount('');
      setGpuFrequency('');
      setMemoryCount('');
      setMemoryFrequency('');
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!gpuCount || !gpuFrequency || !memoryCount || !memoryFrequency) {
      setError('All fields are required');
      return;
    }

    const graphicCardData = {
      gpuCount: parseInt(gpuCount, 10),
      gpuFrequency: parseFloat(gpuFrequency).toFixed(2),
      memoryCount: parseInt(memoryCount, 10),
      memoryFrequency: parseFloat(memoryFrequency).toFixed(2), 
    };
    onSave(graphicCardData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {graphicCardId ? 'Edit Graphic Card' : 'Add Graphic Card'}
        </Typography>
        {error && <Typography color="error">{error}</Typography>}
        <form onSubmit={handleSubmit}>
          <TextField
            label="GPU Count"
            type="number"
            value={gpuCount}
            onChange={(e) => setGpuCount(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="GPU Frequency (GHz)"
            type="number"
            value={gpuFrequency}
            onChange={(e) => {
              const value = e.target.value;
              if (/^\d*\.?\d{0,2}$/.test(value)) {
                setGpuFrequency(value);
              }
            }}
            inputProps={{
              step: "0.01",
            }}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Memory Count (GB)"
            type="number"
            value={memoryCount}
            onChange={(e) => setMemoryCount(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Memory Frequency (GHz)"
            type="number"
            value={memoryFrequency}
            onChange={(e) => {
              const value = e.target.value;
              if (/^\d*\.?\d{0,2}$/.test(value)) {
                setMemoryFrequency(value);
              }
            }}
            inputProps={{
              step: "0.01",
            }}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {graphicCardId ? 'Save Changes' : 'Add Graphic Card'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default GraphicCardFormModal;