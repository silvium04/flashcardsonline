import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { authFetch } from "./authFetch";
import "./DeckDetail.css";

const apiUrl = process.env.REACT_APP_API_URL;

const DeckDetail = () => {
  const { deckId } = useParams();
  const navigate = useNavigate();
  const [cards, setCards] = useState([]);
  const [frontText, setFrontText] = useState("");
  const [backText, setBackText] = useState("");
  const [deleteMode, setDeleteMode] = useState(false);
  const [editMode, setEditMode] = useState(false);

  const [showDeletePopup, setShowDeletePopup] = useState(false);
  const [showEditPopup, setShowEditPopup] = useState(false);
  const [selectedCard, setSelectedCard] = useState(null);
  const [tempQuestion, setTempQuestion] = useState("");
  const [tempAnswer, setTempAnswer] = useState("");

  useEffect(() => {
    const fetchFlashcards = async () => {
      try {
        const response = await authFetch(`${apiUrl}/api/flashcards/deck/${deckId}`);
        if (response.ok) {
          const data = await response.json();
          const cardsWithFlip = data.map((card) => ({ ...card, flipped: false }));
          setCards(cardsWithFlip);
        } else {
          console.error("Fehler beim Laden der Flashcards");
        }
      } catch (err) {
        console.error("Netzwerkfehler", err);
      }
    };

    fetchFlashcards();
  }, [deckId]);

  const handleAddCard = async () => {
    if (frontText && backText) {
      try {
        const response = await authFetch(`${apiUrl}/api/flashcards/create`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            frontText: frontText,
            backText: backText,
            deck: { deckId: parseInt(deckId) },
          }),
        });

        if (response.ok) {
          const savedCard = await response.json();
          setCards((prev) => [...prev, { ...savedCard, flipped: false }]);
          setFrontText("");
          setBackText("");
        } else {
          console.error("Konnte Karte nicht speichern");
        }
      } catch (err) {
        console.error("Netzwerkfehler beim Speichern", err);
      }
    }
  };

  const toggleFlip = (cardId) => {
    setCards((prevCards) =>
      prevCards.map((card) =>
        card.id === cardId ? { ...card, flipped: !card.flipped } : card
      )
    );
  };

  const confirmDelete = (cardId) => {
    setSelectedCard(cardId);
    setShowDeletePopup(true);
  };

  const handleDeleteConfirmed = async () => {
    try {
      const response = await authFetch(`${apiUrl}/api/flashcards/delete/${selectedCard}`, {
        method: "DELETE",
      });
      if (response.ok) {
        setCards((prevCards) => prevCards.filter((card) => card.id !== selectedCard));
      } else {
        console.error("Konnte Karte nicht löschen");
      }
    } catch (err) {
      console.error("Netzwerkfehler beim Löschen", err);
    }
    setShowDeletePopup(false);
    setSelectedCard(null);
  };

  const startEdit = (card) => {
    setSelectedCard(card.id);
    setTempQuestion(card.frontText);
    setTempAnswer(card.backText);
    setShowEditPopup(true);
  };

  const handleEditConfirmed = async () => {
    if (!tempQuestion || !tempAnswer) return;

    try {
      const response = await authFetch(`${apiUrl}/api/flashcards/update/`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          flashcardId: selectedCard,
          frontText: tempQuestion,
          backText: tempAnswer,
          deck: { deckId: parseInt(deckId) },
        }),
      });

      if (response.ok) {
        const updatedCard = await response.json();
        setCards((prevCards) =>
          prevCards.map((card) =>
            card.id === selectedCard ? { ...updatedCard, flipped: false } : card
          )
        );
      } else {
        console.error("Konnte Karte nicht aktualisieren");
      }
    } catch (err) {
      console.error("Netzwerkfehler beim Aktualisieren", err);
    }

    setShowEditPopup(false);
    setSelectedCard(null);
    setTempQuestion("");
    setTempAnswer("");
  };

  return (
    <div className="deck-detail">
      <h2>Deck {deckId}</h2>

      <div className="card-form">
        <input
          type="text"
          placeholder="Question"
          value={frontText}
          onChange={(e) => setFrontText(e.target.value)}
        />
        <input
          type="text"
          placeholder="Answer"
          value={backText}
          onChange={(e) => setBackText(e.target.value)}
        />
        <button onClick={handleAddCard}>add</button>
        <button
          onClick={() => {
            setDeleteMode(!deleteMode);
            setEditMode(false);
          }}
        >
          {deleteMode ? "stop deleting" : "delete cards"}
        </button>
        <button
          onClick={() => {
            setEditMode(!editMode);
            setDeleteMode(false);
          }}
        >
          {editMode ? "stop editing" : "edit cards"}
        </button>
      </div>

      <div className="card-grid">
        {cards.map((card) => (
          <div
            key={card.id}
            className={`flashcard ${card.flipped ? "flipped" : ""} ${
              deleteMode ? "delete-mode" : ""
            } ${editMode ? "edit-mode" : ""}`}
            onClick={() => {
              if (deleteMode) confirmDelete(card.id);
              else if (editMode) startEdit(card);
              else toggleFlip(card.id);
            }}
          >
            {card.flipped ? card.backText : card.frontText}
          </div>
        ))}
      </div>

      {showDeletePopup && (
        <div className="popup-overlay">
          <div className="popup-content">
            <p>Do you really want to delete this card?</p>
            <div className="button-group">
              <div className="submit" onClick={handleDeleteConfirmed}>
                Delete
              </div>
              <div className="submit" onClick={() => setShowDeletePopup(false)}>
                Cancel
              </div>
            </div>
          </div>
        </div>
      )}

      {showEditPopup && (
        <div className="popup-overlay">
          <div className="popup-content">
            <p>Edit card:</p>
            <input
              type="text"
              value={tempQuestion}
              onChange={(e) => setTempQuestion(e.target.value)}
              placeholder="Question"
            />
            <input
              type="text"
              value={tempAnswer}
              onChange={(e) => setTempAnswer(e.target.value)}
              placeholder="Answer"
            />
            <div className="button-group">
              <div className="submit" onClick={handleEditConfirmed}>
                Save
              </div>
              <div className="submit" onClick={() => setShowEditPopup(false)}>
                Cancel
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DeckDetail;
