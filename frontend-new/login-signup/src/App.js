import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Signup from "./Signup";
import Navbar from "./Navbar";
import HomePage from "./HomePage";
import DeckOverview from "./DeckOverview";
import DeckDetail from "./DeckDetail";
import Learnmode from "./Learnmode";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/decks" element={<DeckOverview />} />
        <Route path="/deck/:id" element={<DeckDetail />} />{" "}
        <Route path="/learn/:id" element={<Learnmode />} />
        {/* ❗ Muss vorhanden sein */}
        <Route path="*" element={<Login />} /> {/* Fallback ganz unten */}
      </Routes>
    </Router>
  );
}

export default App;
