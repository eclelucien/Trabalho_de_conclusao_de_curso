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
    // Call the logout function from your authentication context
    logout();
    navigate('/login');

    // Close the menu
    handleMenuClose();
    // Redirect to the home page or any other desired page after logout
    navigate('/');
  };

  const handlePersonClick = (event) => {
    // If the user is authenticated, show the menu; otherwise, redirect to login
    if (user) {
      handleMenuClick(event);
    } else {
      // Redirect to the login page if not authenticated
      navigate('/login');
    }
  };

  const handleCartClick = () => {
    // Check if the user is authenticated before navigating to the cart
    if (!user) {
      // Redirect to the login page if not authenticated
      navigate('/login');
    } else {
      // Perform the desired action (e.g., navigate to the cart)
      // Replace '/cart' with the actual path you want to navigate to
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
          {/* Person Icon with Menu */}
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
              // Show logout and profile options if the user is authenticated
              <>
                <MenuItem onClick={handleLogout}>Logout</MenuItem>
                <MenuItem onClick={() => navigate('/profile')}>Perfil</MenuItem>
              </>
            ) : null}
          </Menu>
          {/* Shopping Cart Icon */}
          <IconButton color="inherit" onClick={handleCartClick}>
            <Badge badgeContent={3} color="error">
              <ShoppingCart />
            </Badge>
          </IconButton>
        </div>
      </Toolbar>
    </AppBar>
  );
}

export default StoreAppBar;
