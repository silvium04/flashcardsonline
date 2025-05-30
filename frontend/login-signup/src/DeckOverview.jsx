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
  const [showPopup, setShowPopup] = useState(false);
  const [dropdownValue, setDropdownValue] = useState("normal");
  const [selectedDeck, setSelectedDeck] = useState(null);


  useEffect(() => {
    const fetchDecks = async () => {
      try {
        const response = await authFetch(`${apiUrl}/api/decks/getAllDecksForUser`, {
          method: "GET",
        });

        if (response.ok) {
          const data = await response.json();
          setDecks(data); // Hier setzen wir die Decks vom Server
        } else {
          console.error("Fehler beim Laden der Decks:", response.status);
        }
      } catch (err) {
        console.error("Netzwerkfehler:", err);
      }
    };


    fetchDecks(); // Funktion aufrufen
  }, []);


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




  const handleCreateDeck = async () => {
    const name = prompt("Name des neuen Decks:");
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
          <button onClick={handleCreateDeck}>New Deck</button>
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
                    navigate(`/deck/${selectedDeck}`);
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
