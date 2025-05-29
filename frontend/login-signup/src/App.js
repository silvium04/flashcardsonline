import React from "react";
import {BrowserRouter as Router, Routes, Route, useLocation} from "react-router-dom";
import Login from "./Login";
import Signup from "./Signup";
import Navbar from "./Navbar";
import HomePage from "./HomePage";
import DeckOverview from "./DeckOverview";
import DeckDetail from "./DeckDetail";
import Learnmode from "./Learnmode";
import Profile from "./Profile";

function NavbarWrapper() {
    const location = useLocation();
    const hideNavbarPaths = ['/', '/login', '/signup'];
    const showNavbar = !hideNavbarPaths.includes(location.pathname);

    return showNavbar ? <Navbar /> : null;
}


function App() {
  return (
    <Router>
      <NavbarWrapper/>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/decks" element={<DeckOverview />} />
        <Route path="/deck/:id" element={<DeckDetail />} />{" "}
        <Route path="/learn/:id" element={<Learnmode />} />
        <Route path="/profile" element={<Profile />} />s
        {/* Muss vorhanden sein */}
        <Route path="*" element={<HomePage />} />
      </Routes>
    </Router>
  );
}

export default App;
