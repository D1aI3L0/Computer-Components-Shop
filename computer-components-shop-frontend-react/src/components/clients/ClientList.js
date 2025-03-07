import React, { useEffect, useState } from 'react';
import { getClients, deleteClient, updateClient, createClient } from '../../services/api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import ClientFormModal from './ClientFormModal';

const ClientList = () => {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingClientId, setEditingClientId] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    const fetchClients = async () => {
      try {
        const response = await getClients();
        setClients(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchClients();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteClient(id);
      setClients(clients.filter((client) => client.id !== id));
    } catch (err) {
      console.error('Failed to delete client:', err);
    }
  };

  const handleEdit = (id) => {
    setEditingClientId(id);
    setIsModalOpen(true);
  };

  const handleAdd = () => {
    setEditingClientId(null);
    setIsModalOpen(true);
  };

  const handleSave = async (clientData) => {
    try {
      if (editingClientId) {
        await updateClient(editingClientId, clientData);
      } else {
        const response = await createClient(clientData);
        setClients([...clients, response.data]);
      }
      setIsModalOpen(false);

      const response = await getClients();
      setClients(response.data);
    } catch (err) {
      console.error('Failed to save client:', err);
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingClientId(null);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Clients</h2>
      <Button variant="contained" color="primary" onClick={handleAdd}>
        Add Client
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Surname</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {clients.map((client) => (
              <TableRow key={client.id}>
                <TableCell>{client.name}</TableCell>
                <TableCell>{client.surname}</TableCell>
                <TableCell>{client.emailAddress}</TableCell>
                <TableCell>{client.phoneNumber}</TableCell>
                <TableCell>
                  <IconButton color="primary" onClick={() => handleEdit(client.id)}>
                    <EditIcon />
                  </IconButton>
                  <IconButton color="secondary" onClick={() => handleDelete(client.id)}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <ClientFormModal
        open={isModalOpen}
        onClose={handleCloseModal}
        onSave={handleSave}
        clientId={editingClientId}
        initialData={clients.find((client) => client.id === editingClientId)}
      />
    </div>
  );
};

export default ClientList;