import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import ClientList from './components/clients/ClientList';
import ProcessorList from './components/processors/ProcessorList';
import MotherboardList from './components/motherboards/MotherboardList';
import GraphicCardList from './components/graphicCards/GraphicCardList';
import ProductList from './components/products/ProductList';
import OrderList from './components/orders/OrderList';
import ReviewList from './components/reviews/ReviewList';

const App = () => {
  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Shop Management
          </Typography>
          <Button color="inherit" component={Link} to="/clients">
            Clients
          </Button>
          <Button color="inherit" component={Link} to="/processors">
            Processors
          </Button>
          <Button color="inherit" component={Link} to="/motherboards">
            Motherboards
          </Button>
          <Button color="inherit" component={Link} to="/graphic-cards">
            Graphic cards
          </Button>
          <Button color="inherit" component={Link} to="/produts">
            Products
          </Button>
          <Button color="inherit" component={Link} to="/orders">
            Orders
          </Button>
          <Button color="inherit" component={Link} to="/reviews">
            Reviews
          </Button>
        </Toolbar>
      </AppBar>
      <Container>
        <Routes>
          <Route path="/clients" element={<ClientList />} />
          <Route path="/processors" element={<ProcessorList />} />
          <Route path="/motherboards" element={<MotherboardList />} />
          <Route path="/graphic-cards" element={<GraphicCardList />} />
          <Route path="/produts" element={<ProductList />} />
          <Route path="/orders" element={<OrderList />} />
          <Route path="/reviews" element={<ReviewList />} />
          <Route path="/" element={<ClientList />} />
        </Routes>
      </Container>
    </Router>
  );
};

export default App;