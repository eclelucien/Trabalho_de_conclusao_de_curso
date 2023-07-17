import React from 'react';
import { AppBar, Toolbar, Typography, IconButton, Badge } from '@mui/material';
import { ShoppingCart } from '@mui/icons-material';
import logo from './lucienstore-logo.png'; // Replace 'logo.png' with the path to your logo image

function StoreAppBar() {
  return (
    <AppBar position="static">
      <Toolbar>
        <img src={logo} alt="Logo" style={{ height: '40px', marginRight: '10px' }} />
        <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
          LucienStore
        </Typography>
        <div style={{ marginLeft: 'auto' }}>
          <IconButton color="inherit">
            <Badge badgeContent={3} color="error">
              <ShoppingCart />
            </Badge>
          </IconButton>
          {/* Add more navigation items, buttons, or icons as needed */}
        </div>
      </Toolbar>
    </AppBar>
  );
}

export default StoreAppBar;
