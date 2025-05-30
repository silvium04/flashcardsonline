import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { authFetch } from "./authFetch";
import "./DeckDetail.css";

const apiUrl = process.env.REACT_APP_API_URL;

const DeckDetail = () => {
  const { deckId } = useParams();
  const navigate = useNavigate();
  const [cards, setCards] = useState([]);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [deleteMode, setDeleteMode] = useState(false);
  const [editMode, setEditMode] = useState(false);

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

  // 🔧 START ADD: Neue Karte per POST speichern
  const handleAddCard = async () => {
    if (question && answer) {
      try {
        const response = await authFetch(`${apiUrl}/api/flashcards`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            frontText: question,
            backText: answer,
            deck: { deckId: parseInt(deckId) },
          }),
        });

        if (response.ok) {
          const savedCard = await response.json();
          setCards((prev) => [...prev, { ...savedCard, flipped: false }]);
          setQuestion("");
          setAnswer("");
        } else {
          console.error("Konnte Karte nicht speichern");
        }
      } catch (err) {
        console.error("Netzwerkfehler beim Speichern", err);
      }
    }
  };
  // 🔧 ENDE ADD

  const toggleFlip = (cardId) => {
    setCards((prevCards) =>
      prevCards.map((card) =>
        card.id === cardId ? { ...card, flipped: !card.flipped } : card
      )
    );
  };

  // 🔧 START DELETE: Karte per DELETE im Backend entfernen
  const handleDelete = async (cardId) => {
    const confirmed = window.confirm("Do you really want to delete this card?");
    if (confirmed) {
      try {
        const response = await authFetch(`${apiUrl}/api/flashcards/${cardId}`, {
          method: "DELETE",
        });
        if (response.ok) {
          setCards((prevCards) => prevCards.filter((card) => card.id !== cardId));
        } else {
          console.error("Konnte Karte nicht löschen");
        }
      } catch (err) {
        console.error("Netzwerkfehler beim Löschen", err);
      }
    }
  };
  // 🔧 ENDE DELETE

  // 🔧 START EDIT: Karte per PUT im Backend aktualisieren
  const handleEdit = async (cardId) => {
    const cardToEdit = cards.find((card) => card.id === cardId);
    if (!cardToEdit) return;

    const newQuestion = prompt("New Question:", cardToEdit.question);
    const newAnswer = prompt("New Answer:", cardToEdit.answer);

    if (newQuestion && newAnswer) {
      try {
        const response = await authFetch(`${apiUrl}/api/flashcards`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            flashcardId: cardId,
            frontText: newQuestion,
            backText: newAnswer,
            deck: { deckId: parseInt(deckId) },
          }),
        });

        if (response.ok) {
          const updatedCard = await response.json();
          setCards((prevCards) =>
            prevCards.map((card) =>
              card.id === cardId ? { ...updatedCard, flipped: false } : card
            )
          );
        } else {
          console.error("Konnte Karte nicht aktualisieren");
        }
      } catch (err) {
        console.error("Netzwerkfehler beim Aktualisieren", err);
      }
    }
  };
  // 🔧 ENDE EDIT

  return (
    <div className="deck-detail">
      <h2>Deck {deckId}</h2>

      <div className="card-form">
        <input
          type="text"
          placeholder="Question"
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
        />
        <input
          type="text"
          placeholder="Answer"
          value={answer}
          onChange={(e) => setAnswer(e.target.value)}
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
            }`}
            onClick={() => {
              if (deleteMode) handleDelete(card.id);
              else if (editMode) handleEdit(card.id);
              else toggleFlip(card.id);
            }}
          >
            {card.flipped ? card.answer : card.question}
          </div>
        ))}
      </div>
    </div>
  );
};

export default DeckDetail;
