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

const ProcessorFormModal = ({ open, onClose, onSave, processorId, initialData }) => {
  const [threadsCount, setThreadsCount] = useState(0);
  const [clockFrequency, setClockFrequency] = useState(0);
  const [maxFrequency, setMaxFrequency] = useState(0);
  const [cpuCount, setCpuCount] = useState(0);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (initialData) {
      setThreadsCount(initialData.threadsCount || null);
      setClockFrequency(formatFloat(initialData.clockFrequency) || null);
      setMaxFrequency(formatFloat(initialData.maxFrequency) || null);
      setCpuCount(initialData.cpuCount || null);
    } else {
      setThreadsCount(null);
      setClockFrequency(null);
      setMaxFrequency(null);
      setCpuCount(null);
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!threadsCount || !clockFrequency || !maxFrequency || !cpuCount) {
      setError('All fields are required');
      return;
    }

    const processorData = {
      threadsCount: parseInt(threadsCount, 10),
      clockFrequency: parseFloat(clockFrequency).toFixed(2),
        maxFrequency: parseFloat(maxFrequency).toFixed(2),
      cpuCount: parseInt(cpuCount, 10),
    };
    onSave(processorData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
            {processorId ? 'Edit Processor' : 'Add Processor'}
        </Typography>
        {error && <Typography color="error">{error}</Typography>}
        <form onSubmit={handleSubmit}>
            <TextField
                label="Threads Count"
                type="number"
                value={threadsCount}
                onChange={(e) => setThreadsCount(formatFloat(e.target.value))}
                fullWidth
                margin="normal"
                required
            />
            <TextField
                label="Clock Frequency (GHz)"
                type="number"
                value={clockFrequency}
                onChange={(e) => {
                  const value = e.target.value;
                  if (/^\d*\.?\d{0,2}$/.test(value)) {
                    setClockFrequency(value);
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
                label="Max Frequency (GHz)"
                type="number"
                value={maxFrequency}
                onChange={(e) =>  setMaxFrequency(formatFloat(e.target.value))}
                inputProps={{
                  step: "0.01",
                }}
                fullWidth
                margin="normal"
                required
            />
          <TextField
            label="CPU Count"
            type="number"
            value={cpuCount}
            onChange={(e) => setCpuCount(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {processorId ? 'Save Changes' : 'Add Processor'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default ProcessorFormModal;