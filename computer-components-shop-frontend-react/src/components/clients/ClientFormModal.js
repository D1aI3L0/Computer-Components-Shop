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

const ClientFormModal = ({ open, onClose, onSave, clientId, initialData }) => {
  const [name, setName] = useState(initialData?.name || '');
  const [surname, setSurname] = useState(initialData?.surname || '');
  const [emailAddress, setEmail] = useState(initialData?.emailAddress || '');
  const [phoneNumber, setPhoneNumber] = useState(initialData?.phoneNumber || '');
  const [error, setError] = useState(null);

  useEffect(() => {
    if (initialData) {
      setName(initialData.name || '');
      setSurname(initialData.surname || '');
      setEmail(initialData.emailAddress || '');
      setPhoneNumber(initialData.phoneNumber || '');
    } else {
      setName('');
      setSurname('');
      setEmail('');
      setPhoneNumber('');
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name || !surname || !emailAddress) {
      setError('All fields are required');
      return;
    }

    const clientData = { name, surname, emailAddress, phoneNumber };
    onSave(clientData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {clientId ? 'Edit Client' : 'Add Client'}
        </Typography>
        {error && <Typography color="error">{error}</Typography>}
        <form onSubmit={handleSubmit}>
          <TextField
            label="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Surname"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Email"
            type="email"
            value={emailAddress}
            onChange={(e) => setEmail(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <TextField
            label="Email"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
            fullWidth
            margin="normal"
            required
          />
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {clientId ? 'Save Changes' : 'Add Client'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default ClientFormModal;