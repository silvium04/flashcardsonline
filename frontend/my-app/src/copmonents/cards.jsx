import { useState } from "react";
import "./cards.css";

function Flashcard({ question, answer }) {
  const [showAnswer, setShowAnswer] = useState(false);

  return (
    <div className="flashcard">
      <div className="flashcard-content">
        {showAnswer ? (
          <p className="flashcard-answer">{answer}</p>
        ) : (
          <h3 className="flashcard-question">{question}</h3>
        )}
      </div>
      <button
        className="flashcard-button"
        onClick={() => setShowAnswer(!showAnswer)}
      >
        {showAnswer ? "Frage anzeigen" : "Antwort anzeigen"}
      </button>
    </div>
  );
}

export default Flashcard;
