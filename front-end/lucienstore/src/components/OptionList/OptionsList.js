import React from 'react';

import './OptionsList.css';
import '../../App.css';


const OptionsList = () => {
  const options = [
    'New Arrivals',
    'Best Sellers',
    'Clearance',
    'Featured Products',
    'Top Rated',
    'On Sale',
    'Seasonal Offers',
    'Recommended',
    'Exclusive Deals',
    'Limited Stock',
    'Popular Categories',
    'Customer Favorites',

  ];

  return (
    <div className="options-list">
      <h2>Options</h2>
      <ul>
        {options.map((option, index) => (
          <li key={index}>{option}</li>
        ))}
      </ul>
    </div>
  );
};

export default OptionsList;
