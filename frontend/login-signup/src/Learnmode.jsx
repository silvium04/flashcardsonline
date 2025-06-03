import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./Learnmode.css";
import { authFetch } from "./authFetch";

const Learnmode = () => {
  const { deckId, mode } = useParams();
  const navigate = useNavigate();
  const [cards, setCards] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [flipped, setFlipped] = useState(false);
  const [leitnerButton, setLeitnerButton] = useState();

  const currentCard = cards[currentIndex];

  const fetchCards = async () => {
    try {
      if (mode === "normal") {
        const response = await authFetch(
          `${process.env.REACT_APP_API_URL}/api/learning/normal/${deckId}`,
          {
            method: "GET",
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch random cards");
        }
        if (response.ok) {
          const data = await response.json();
          setCards(data);
        } else {
          console.error("Error fetching cards:", response.status);
        }
      } else if (mode === "backwards") {
        const response = await authFetch(
          `${process.env.REACT_APP_API_URL}/api/learning/backwards/${deckId}`,
          {
            method: "GET",
          }
        );
        if (response.ok) {
          const data = await response.json();
          setCards(data);
        } else {
          console.error("Error fetching cards:", response.status);
        }
      } else if (mode === "random") {
        const response = await authFetch(
          `${process.env.REACT_APP_API_URL}/api/learning/random/${deckId}`,
          {
            method: "GET",
          }
        );
        if (response.ok) {
          const data = await response.json();
          setCards(data);
        } else {
          console.error("Error fetching cards:", response.status);
        }
      } else if (mode === "leitner") {
        const response = await authFetch(
          `${process.env.REACT_APP_API_URL}/api/learning/leitner/${deckId}`,
          {
            method: "GET",
          }
        );
        if (response.ok) {
          const data = await response.json();
          setCards(data);
        } else {
          console.error("Error fetching cards:", response.status);
        }
      } else {
        console.error("Invalid mode:", mode);
      }
    } catch (err) {
      console.error("Network error:", err);
    }
  };

  useEffect(() => {
    fetchCards();
  }, []);

  const updateLeitnerCard = async () => {
    try {
      const response = await authFetch(
        `${process.env.REACT_APP_API_URL}/api/learning/updateLeitnerFlashcard`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            flashcardId: currentCard.flashcardId,
          }),
        }
      );
    } catch (err) {
      console.error("Error updating Leitner card:", err);
    }
  };

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
      <div className="deck-title">Deck {deckId}</div>

      <div className="card-display" onClick={() => setFlipped(!flipped)}>
        {flipped ? currentCard.frontText : currentCard.backText}
      </div>

      {mode === "leitner" && (
        <button
          className="exit"
          value={leitnerButton}
          onClick={() => {
            updateLeitnerCard();
          }}
        >
          correct
        </button>
      )}

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
