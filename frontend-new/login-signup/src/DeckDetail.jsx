import React, { useState } from "react";
import { useParams } from "react-router-dom";
import "./DeckDetail.css";

const DeckDetail = () => {
  const { id } = useParams();
  const [cards, setCards] = useState([]);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");

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

  return (
    <div className="deck-detail">
      <h2>Deck {id}</h2>

      <div className="card-form">
        <input
          type="text"
          placeholder="Frage"
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
        />
        <input
          type="text"
          placeholder="Antwort"
          value={answer}
          onChange={(e) => setAnswer(e.target.value)}
        />
        <button onClick={handleAddCard}>Karteikarte erstellen</button>
      </div>

      <div className="card-grid">
        {cards.map((card) => (
          <div
            key={card.id}
            className={`flashcard ${card.flipped ? "flipped" : ""}`}
            onClick={() => toggleFlip(card.id)}
          >
            {card.flipped ? card.answer : card.question}
          </div>
        ))}
      </div>
    </div>
  );
};

export default DeckDetail;
