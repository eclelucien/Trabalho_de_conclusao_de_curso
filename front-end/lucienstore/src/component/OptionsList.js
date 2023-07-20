import React from 'react';
import './OptionsList.css';

const OptionsList = () => {
  const options = ['News', 'Tendency', 'In high', 'Promotion', 'Promotion', 'Promotion', 'Promotion'];

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
