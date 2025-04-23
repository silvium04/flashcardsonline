import React from 'react';
import './SidebySideDiv.css';

const SideBySideDiv = () => {
  return (
    <div><h1 className='center'>Decks</h1>
    <div className="sideBySideContainer">
      <div className="sideBySideItem" style={{ backgroundColor: 'cyan' }}></div>
      <div className="sideBySideItem" style={{ backgroundColor: 'cyan' }}></div>
      <div className="sideBySideItem" style={{ backgroundColor: "cyan" }}></div>
      
<a href="#" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Read more</a>

    </div>
    </div>
  );
};

export default SideBySideDiv; // Ensure this is a default export