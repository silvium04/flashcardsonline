import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Signup from "./Signup";
import Navbar from "./Navbar";
import HomePage from "./HomePage";
import DeckOverview from "./DeckOverview";
import DeckDetail from "./DeckDetail";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/decks" element={<DeckOverview />} />
        <Route path="/decks/:id" element={<DeckDetail />} />
        <Route path="*" element={<Login />} />
      </Routes>
    </Router>
  );
}

export default App;
