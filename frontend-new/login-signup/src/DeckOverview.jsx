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

  const filteredDecks = decks.filter((deck) =>
    deck.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleDelete = (deckId) => {
    const confirmed = window.confirm(
      "Möchtest du dieses Deck wirklich löschen?"
    );
    if (confirmed) {
      setDecks((prev) => prev.filter((deck) => deck.id !== deckId));
      setMode(null);
    }
  };

  const handleEdit = (deckId) => {
    const newName = prompt("Neuer Name für das Deck:");
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
          <button onClick={() => setMode("delete")}>Löschen</button>
          <button onClick={() => setMode("edit")}>Bearbeiten</button>
          <input
            type="text"
            placeholder="Suchen..."
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
                e.stopPropagation(); // verhindert Klick auf Karte
                navigate(`/learn/${deck.id}`);
              }}
            >
              Lernen starten
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default DeckOverview;
