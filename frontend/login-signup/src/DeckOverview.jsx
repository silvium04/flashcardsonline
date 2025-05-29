import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./DeckOverview.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";

const initialDecks = [
  { id: 1, name: "Unit 1" },
  { id: 2, name: "Unit 2" },
  { id: 3, name: "Unit 3" },
  { id: 4, name: "Unidad 1" },
  { id: 5, name: "Unidad 2" },
];

const DeckOverview = () => {
  const [search, setSearch] = useState("");
  const [decks, setDecks] = useState(initialDecks);
  const [mode, setMode] = useState(null); // 'delete' or 'edit'
  const navigate = useNavigate();
  const [showPopup, setShowPopup] = useState(false);
  const [dropdownValue, setDropdownValue] = useState("normal");
  const [selectedDeck, setSelectedDeck] = useState(null);


  const filteredDecks = decks.filter((deck) =>
    deck.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleDelete = (deckId) => {
    const confirmed = window.confirm(
      "Do you really want to delete this deck?"
    );
    if (confirmed) {
      setDecks((prev) => prev.filter((deck) => deck.id !== deckId));
      setMode(null);
    }
  };

  const handleEdit = (deckId) => {
    const newName = prompt("New deck name:");
    if (newName) {
      setDecks((prev) =>
        prev.map((deck) =>
          deck.id === deckId ? { ...deck, name: newName } : deck
        )
      );
      setMode(null);
    }
  };

  return (
    <div className="deck-overview">
      <div className="deck-header">
        <h2>Decks</h2>
        <div className="deck-controls">
          <button onClick={() => setMode("delete")}>Delete</button>
          <button onClick={() => setMode("edit")}>Edit</button>
          <input
            type="text"
            placeholder="Search..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
      </div>

      <div className="deck-grid">
        {filteredDecks.map((deck) => (
          <div
            key={deck.id}
            className={`deck-card ${mode === "delete" ? "delete-mode" : ""} ${
              mode === "edit" ? "edit-mode" : ""
            }`}
            onClick={() => {
              if (mode === "delete") handleDelete(deck.id);
              else if (mode === "edit") handleEdit(deck.id);
              else navigate(`/deck/${deck.id}`);
            }}
          >
            <div className="deck-box">
              <span className="deck-name">{deck.name}</span>

              {mode === "edit" && (
                <FontAwesomeIcon icon={faPen} className="edit-icon" />
              )}
            </div>
            <button
              className="learn-button"
              onClick={(e) => {
                e.stopPropagation();
                setSelectedDeck(deck.id);
                setShowPopup(true);
              }}
            >
              Start Learning
            </button>
          </div>
        ))}
      </div>
      {showPopup && (
          <div className="popup-overlay">
            <div className="popup-content">
              <h3>choose learnigmode</h3>
              <select
                  value={dropdownValue}
                  onChange={(e) => setDropdownValue(e.target.value)}
              >
                <option value="normal">normal</option>
                <option value="backwards">backwards</option>
                <option value="random">random</option>
                <option value="leitner">Leitner System</option>
              </select>
              <div >
                <div align={"center"}>
                  <button
                      className="submit"
                      onClick={() => {
                        setShowPopup(false);
                        // Hier könntest du z.B. navigate(`/learn/${selectedDeck}?mode=${dropdownValue}`)
                      }}
                  >
                    Start
                  </button>
                </div>
                <div align={"center"}>
                  <button
                      className="submit"
                      onClick={() => setShowPopup(false)}
                  >
                    Cancel
                  </button>
                </div>

              </div>
            </div>
          </div>
      )}
    </div>
  );
};

export default DeckOverview;
