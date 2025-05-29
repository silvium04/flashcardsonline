import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./Learnmode.css";

const sampleDecks = {
  1: [
    {
      id: 1,
      question: "Who founded Apple?",
      answer: "Steve Jobs",
    },
    {
      id: 2,
      question: "Was ist JSX?",
      answer: "Syntax-Erweiterung für JavaScript.",
    },
  ],
  // Weitere Decks können hier simuliert werden
};

const Learnmode = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const cards = sampleDecks[id] || [];
  const [currentIndex, setCurrentIndex] = useState(0);
  const [flipped, setFlipped] = useState(false);

  const currentCard = cards[currentIndex];

  const handleNext = () => {
    if (currentIndex < cards.length - 1) {
      setCurrentIndex(currentIndex + 1);
      setFlipped(false);
    }
  };

  const handlePrev = () => {
    if (currentIndex > 0) {
      setCurrentIndex(currentIndex - 1);
      setFlipped(false);
    }
  };

  if (!currentCard) {
    return (
      <div className="learnmode">
        <h2>My Cards.</h2>
        <button onClick={() => navigate("/decks")}>back</button>
      </div>
    );
  }

  return (
    <div className="learnmode">
      <div className="deck-title">Deck {id}</div>

      <div className="card-display" onClick={() => setFlipped(!flipped)}>
        {flipped ? currentCard.answer : currentCard.question}
      </div>

      <div className="controls">
        <button onClick={handlePrev}>&larr;</button>
        <button onClick={() => setFlipped(!flipped)} className="flip">
          {flipped ? "show Question" : "show Answer"}
        </button>
        <button onClick={handleNext}>&rarr;</button>
      </div>

      <button className="exit" onClick={() => navigate("/decks")}>
        stop
      </button>
    </div>
  );
};

export default Learnmode;
