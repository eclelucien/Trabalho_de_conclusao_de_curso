import React from 'react';
import { AppBar, Toolbar, Typography, IconButton, Badge } from '@mui/material';
import { ShoppingCart } from '@mui/icons-material';
import logo from '../assets/images/lucienstore-logo.png';
import '../App.css';

function StoreAppBar() {
  return (
    <AppBar position="static">
      <Toolbar>
        <img src={logo} alt="Logo" style={{ height: '40px', marginRight: '10px' }} />
        <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
          Lucien Store
        </Typography>
        <div style={{ marginLeft: 'auto' }}>
          <IconButton color="inherit">
            <Badge badgeContent={3} color="error">
              <ShoppingCart />
            </Badge>
          </IconButton>        </div>
      </Toolbar>
    </AppBar>
  );
}

export default StoreAppBar;
