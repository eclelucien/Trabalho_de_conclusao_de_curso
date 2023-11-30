import React, { useState } from 'react';
import { AppBar, Toolbar, Typography, IconButton, Badge, Menu, MenuItem } from '@mui/material';
import { ShoppingCart, Person } from '@mui/icons-material';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import logo from '../assets/images/lucienstore-logo.png';
import '../App.css';

function StoreAppBar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = useState(null);

  const handleMenuClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    logout();
    navigate('/login');

    handleMenuClose();
    navigate('/');
  };

  const handlePersonClick = (event) => {
    if (user) {
      handleMenuClick(event);
    } else {
      navigate('/login');
    }
  };

  const handleCartClick = () => {
    if (!user) {
      navigate('/login');
    } else {
      navigate('/cart');
    }
  };

  return (
    <AppBar position="static">
      <Toolbar>
        <img src={logo} alt="Logo" style={{ height: '40px', marginRight: '10px' }} />
        <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
          Lucien Store
        </Typography>
        <div style={{ marginLeft: 'auto' }}>
          <IconButton color="inherit" onClick={handlePersonClick}>
            <Person />
          </IconButton>
          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleMenuClose}
            onClick={handleMenuClose}
          >
            {user ? (
              <>
                <MenuItem onClick={handleLogout}>Logout</MenuItem>
                <MenuItem onClick={() => navigate('/profile')}>Perfil</MenuItem>
              </>
            ) : null}
          </Menu>
          <IconButton color="inherit" onClick={handleCartClick}>
            <Badge badgeContent={0} color="error">
              <ShoppingCart />
            </Badge>
          </IconButton>
        </div>
      </Toolbar>
    </AppBar>
  );
}

export default StoreAppBar;
