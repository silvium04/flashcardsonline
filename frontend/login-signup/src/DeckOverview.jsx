import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./DeckOverview.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";
import { authFetch } from "./authFetch";

const apiUrl = process.env.REACT_APP_API_URL;

const DeckOverview = () => {
  const [search, setSearch] = useState("");
  const [decks, setDecks] = useState([]);
  const [mode, setMode] = useState(null);
  const navigate = useNavigate();
  const [showLearningPopup, setShowLearningPopup] = useState(false);
  const [dropdownValue, setDropdownValue] = useState("normal");
  const [selectedDeck, setSelectedDeck] = useState(null);

  const [showDeletePopup, setShowDeletePopup] = useState(false);
  const [showEditPopup, setShowEditPopup] = useState(false);
  const [showCreatePopup, setShowCreatePopup] = useState(false);
  const [tempName, setTempName] = useState("");

  const fetchDecks = async () => {
    try {
      const response = await authFetch(`${apiUrl}/api/decks/getAllDecksForUser`, {
        method: "GET",
      });

      if (response.ok) {
        const data = await response.json();
        setDecks(data);
      } else {
        console.error("Fehler beim Laden der Decks:", response.status);
      }
    } catch (err) {
      console.error("Netzwerkfehler:", err);
    }
  };

  useEffect(() => {
    fetchDecks();
  }, []);

  const filteredDecks = decks.filter((deck) =>
    deck.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleDelete = (deckId) => {
    setDecks((prev) => prev.filter((deck) => deck.id !== deckId));
    setMode(null);
  };

  const handleEdit = (deckId, newName) => {
    if (newName) {
      setDecks((prev) =>
        prev.map((deck) =>
          deck.deckId === deckId ? { ...deck, name: newName } : deck
        )
      );
      setMode(null);
    }
  };

  const handleCreateDeck = async (name) => {
    if (!name) return;

    try {
      const response = await authFetch(`${apiUrl}/api/decks/createDeck`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ name: name }),
      });

      if (response.ok) {
        const createdDeck = await response.json();
        setDecks((prev) => [...prev, createdDeck]);
      } else {
        console.error("Fehler beim Erstellen des Decks:", response.status);
      }
    } catch (err) {
      console.error("Netzwerkfehler:", err);
    }
  };

  return (
    <div className="deck-overview">
      <div className="deck-header">
        <h2>Decks</h2>
        <div className="deck-controls">
          <button onClick={() => setMode("delete")}>Delete</button>
          <button onClick={() => setMode("edit")}>Edit</button>
          <button onClick={() => setShowCreatePopup(true)}>New Deck</button>
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
            key={deck.deckId}
            className={`deck-card ${mode === "delete" ? "delete-mode" : ""} ${
              mode === "edit" ? "edit-mode" : ""
            }`}
            onClick={() => {
              if (mode === "delete") {
                setSelectedDeck(deck.deckId);
                setShowDeletePopup(true);
              } else if (mode === "edit") {
                setSelectedDeck(deck.deckId);
                setTempName(deck.name);
                setShowEditPopup(true);
              } else {
                navigate(`/deck/${deck.deckId}`);
              }
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
                setSelectedDeck(deck.deckId);
                setShowLearningPopup(true);
              }}
            >
              Start Learning
            </button>
          </div>
        ))}
      </div>

      {/* Start Learning Popup */}
      {showLearningPopup && (
        <div className="popup-overlay">
          <div className="popup-content">
            <h3>Choose learning mode</h3>
            <select
              value={dropdownValue}
              onChange={(e) => setDropdownValue(e.target.value)}
            >
              <option value="normal">normal</option>
              <option value="backwards">backwards</option>
              <option value="random">random</option>
              <option value="leitner">Leitner System</option>
            </select>
            <div className="button-group">
              <div
                className="submit"
                onClick={() => {
                  setShowLearningPopup(false);
                  navigate(`/learn/${selectedDeck}/${dropdownValue}`);
                }}
              >
                Start
              </div>
              <div className="submit" onClick={() => setShowLearningPopup(false)}>
                Cancel
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Delete Popup */}
      {showDeletePopup && selectedDeck && (
        <div className="popup-overlay">
          <div className="popup-content">
            <p>Do you really want to delete this deck?</p>
            <div className="button-group">
              <div className="submit" onClick={() => {
                handleDelete(selectedDeck);
                setShowDeletePopup(false);
              }}>Delete</div>
              <div className="submit" onClick={() => setShowDeletePopup(false)}>Cancel</div>
            </div>
          </div>
        </div>
      )}

      {/* Edit Popup */}
      {showEditPopup && selectedDeck && (
        <div className="popup-overlay">
          <div className="popup-content">
            <p>Enter new name for deck:</p>
            <input
              type="text"
              value={tempName}
              onChange={(e) => setTempName(e.target.value)}
            />
            <div className="button-group">
              <div className="submit" onClick={() => {
                handleEdit(selectedDeck, tempName);
                setShowEditPopup(false);
                setTempName("");
              }}>Save</div>
              <div className="submit" onClick={() => setShowEditPopup(false)}>Cancel</div>
            </div>
          </div>
        </div>
      )}

      {/* Create Popup */}
      {showCreatePopup && (
        <div className="popup-overlay">
          <div className="popup-content">
            <p>Name of new deck:</p>
            <input
              type="text"
              value={tempName}
              onChange={(e) => setTempName(e.target.value)}
            />
            <div className="button-group">
              <div className="submit" onClick={() => {
                handleCreateDeck(tempName);
                setShowCreatePopup(false);
                setTempName("");
              }}>Create</div>
              <div className="submit" onClick={() => setShowCreatePopup(false)}>Cancel</div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DeckOverview;
