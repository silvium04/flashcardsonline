import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./DeckDetail.css";

const DeckDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [cards, setCards] = useState([]);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [deleteMode, setDeleteMode] = useState(false);
  const [editMode, setEditMode] = useState(false);

  const handleAddCard = () => {
    if (question && answer) {
      const newCard = {
        id: Date.now(),
        question,
        answer,
        flipped: false,
      };
      setCards([...cards, newCard]);
      setQuestion("");
      setAnswer("");
    }
  };

  const toggleFlip = (cardId) => {
    setCards((prevCards) =>
      prevCards.map((card) =>
        card.id === cardId ? { ...card, flipped: !card.flipped } : card
      )
    );
  };

  const handleDelete = (cardId) => {
    const confirmed = window.confirm(
      "Do you really want to delete this card?"
    );
    if (confirmed) {
      setCards((prevCards) => prevCards.filter((card) => card.id !== cardId));
    }
  };

  const handleEdit = (cardId) => {
    const cardToEdit = cards.find((card) => card.id === cardId);
    if (!cardToEdit) return;

    const newQuestion = prompt("New Question:", cardToEdit.question);
    const newAnswer = prompt("New Answer:", cardToEdit.answer);

    if (newQuestion && newAnswer) {
      setCards((prevCards) =>
        prevCards.map((card) =>
          card.id === cardId
            ? { ...card, question: newQuestion, answer: newAnswer }
            : card
        )
      );
    }
  };

  return (
    <div className="deck-detail">
      <h2>Deck {id}</h2>

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
