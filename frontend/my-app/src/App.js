<<<<<<< HEAD
import React from 'react';
import SideBySideDiv from './SidebySideDiv'; // Correct default import

const App = () => {
  return (
    <div>
      <SideBySideDiv />
    </div>
  );
};

export default App;
=======
import logo from './logo.svg';
import './App.css';
import Flashcard from './copmonents/cards';

function App() {
  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <Flashcard question="Was ist React?" answer="Eine JavaScript-Bibliothek für Benutzeroberflächen." />
    </div>
  );
}

export default App;
>>>>>>> dario/test
