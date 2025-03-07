import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, TextField, Button, MenuItem, Select, FormControl, InputLabel } from '@mui/material';
import { getProcessors, getMotherboards, getGraphicCards } from '../../services/api';
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

const productTypes = ['processor', 'motherboard', 'graphic card'];

const ProductFormModal = ({ open, onClose, onSave, productId, initialData }) => {
  const [name, setName] = useState(initialData?.name || '');
  const [price, setPrice] = useState(initialData?.price || '');
  const [manufacturer, setManufacturer] = useState(initialData?.manufacturer || '');
  const [type, setType] = useState(initialData?.type || '');
  const [processors, setProcessors] = useState([]);
  const [motherboards, setMotherboards] = useState([]);
  const [graphicCards, setGraphicCards] = useState([]);
  const [selectedProcessor, setSelectedProcessor] = useState(initialData?.processor || null);
  const [selectedMotherboard, setSelectedMotherboard] = useState(initialData?.motherboard || null);
  const [selectedGraphicCard, setSelectedGraphicCard] = useState(initialData?.graphicCard || null);

  useEffect(() => {
    const fetchData = async () => {
      const processorsData = await getProcessors();
      const motherboardsData = await getMotherboards();
      const graphicCardsData = await getGraphicCards();

      setProcessors(processorsData.data);
      setMotherboards(motherboardsData.data);
      setGraphicCards(graphicCardsData.data);
    };
    fetchData();
  }, []);

  useEffect(() => {
    if (initialData) {
      setName(initialData.name || '');
      setPrice(formatFloat(initialData.price) || '');
      setManufacturer(initialData.manufacturer || '');
      setType(initialData.type || '');
      setSelectedProcessor(initialData.processor || null);
      setSelectedMotherboard(initialData.motherboard || null);
      setSelectedGraphicCard(initialData.graphicCard || null);
    }
  }, [initialData]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const productData = { name, price, manufacturer, type, processor: selectedProcessor, motherboard: selectedMotherboard, graphicCard: selectedGraphicCard };
    console.log(productData);
    onSave(productData);
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={style}>
        <Typography variant="h6" component="h2" sx={{ mb: 2 }}>
          {productId ? 'Edit Product' : 'Add Product'}
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField label="Name" value={name} onChange={(e) => setName(e.target.value)} fullWidth margin="normal" required />
          <TextField label="Price" type="number" 
            value={price} onChange={(e) => {
            const value = e.target.value;
            if (/^\d*\.?\d{0,2}$/.test(value)) {
              setPrice(value);
            };}} 
            fullWidth margin="normal" required />
          <TextField label="Manufacturer" value={manufacturer} onChange={(e) => setManufacturer(e.target.value)} fullWidth margin="normal" required />
          <FormControl fullWidth margin="normal" required>
            <InputLabel>Type</InputLabel>
            <Select value={type} onChange={(e) => setType(e.target.value)}>
              {productTypes.map((productType) => (
                <MenuItem key={productType} value={productType}>
                  {productType}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          {(type === 'processor') && (
            <FormControl fullWidth margin="normal">
              <InputLabel>Processor</InputLabel>
              <Select value={selectedProcessor} onChange={(e) => {setSelectedProcessor(e.target.value);setSelectedGraphicCard(null);setSelectedMotherboard(null)}}>
                {processors.map((proc) => (
                  <MenuItem key={proc.id} value={proc}>{`${proc.cpuCount}CPUs, ${formatFloat(proc.clockFrequency)}GHz`}</MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
          {type === 'motherboard' && (
            <FormControl fullWidth margin="normal">
              <InputLabel>Motherboard</InputLabel>
              <Select value={selectedMotherboard} onChange={(e) => {setSelectedMotherboard(e.target.value); setSelectedGraphicCard(null); setSelectedProcessor(null)}}>
                {motherboards.map((mb) => (
                  <MenuItem key={mb.id} value={mb}>{`${mb.memorySlots} slots, ${mb.chipset}`}</MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
          {(type === 'graphic card') && (
            <FormControl fullWidth margin="normal">
              <InputLabel>Graphic Card</InputLabel>
              <Select value={selectedGraphicCard} onChange={(e) => {setSelectedGraphicCard(e.target.value); setSelectedMotherboard(null); setSelectedProcessor(null)}}>
                {graphicCards.map((gpu) => (
                  <MenuItem key={gpu.id} value={gpu}>{`${gpu.gpuCount} GPUs, ${gpu.memoryCount}GB`}</MenuItem>
                ))}
              </Select>
            </FormControl>
          )}
          <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
            {productId ? 'Save Changes' : 'Add Product'}
          </Button>
          <Button variant="outlined" onClick={onClose} sx={{ mt: 2, ml: 2 }}>
            Cancel
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

export default ProductFormModal;
