import React, { useState } from 'react';
import Faq from 'react-faq-component';
import './Faqs.css';

const styles = {
  bgColor: '#333',
  arrowColor: 'white',
  titleTextColor: 'white',
  transitionDuration: '1s',
  timingFunc: 'ease',
  rowTitleColor: 'white',
  rowTitleTextSize: '20px',
  rowContentColor: 'white',
  rowContentTextSize: '16px',
  rowContentPaddingTop: '10px',
  rowContentPaddingBottom: '10px',
  rowContentPaddingLeft: '50px',
  rowContentPaddingRight: '150px',
};

const Faqs = () => {
  const [questions, setQuestions] = useState([
    {
      title: 'What do I need to rent a car?',
      content: `Each person who intends to drive must bring a valid driver license to drive in India, passport, and a valid means of payment (generally via credit card). All of these items must be submitted on the day the car will be picked up. Kindly pay with an affiliated credit card. If you wish to pay in cash, you must also submit an item of identification (copies are acceptable) in addition to your driver license.`,
    },
    {
      title: 'What should I do if I damage the car?',
      content: `In the event that the customer causes an accident or defacement to the vehicle during the rental period, the user shall be required to pay the non-operation charge (NOC) as partial compensation for the loss of earnings incurred for repairs or cleaning, regardless of the degree of damage or the length of time required for repairs/cleaning. The non-operation charge (NOC) is different from the deductible (paid by the user) in the insurance and compensation system, which is applied in the event of accidents. Please note that this sum is to be paid even if you are subscribed to the Safety Package.`,
    },
    {
      title: 'Do I have to pay a cancellation fee if I cancel my reservation?',
      content: `A reservation cancellation fee pre-determined by the company may apply if you cancel your reservation. No fee shall apply if the cancellation is made at least seven days prior to the date of pick-up. The cancellation fee shall apply for those made six days or less prior to the date of pick-up.`,
    },
    {
      title: 'Can I pay in cash?',
      content: `Yes. We generally accept payment by UPI, credit cards, debit cards, net-banking and wallets, but if the reservation is made in advance, we allow payment in cash at the rental station where the vehicle is to be picked up. If you wish to pay in cash, you must also submit an item of identification (copies are acceptable) in addition to your driver’s license. You will be required to pay by UPI, credit cards, debit cards, net-banking and wallets, if you rent a car from a rental station without a reservation.`,
    },
  ]);

  const [newQuestion, setNewQuestion] = useState('');
  const [newAnswer, setNewAnswer] = useState('');

  const handleQuestionChange = (e) => {
    setNewQuestion(e.target.value);
  };

  const handleAnswerChange = (e) => {
    setNewAnswer(e.target.value);
  };

  const handleAddQuestion = () => {
    if (newQuestion.trim() !== '' && newAnswer.trim() !== '') {
      const newQnA = { title: newQuestion, content: newAnswer };
      setQuestions([...questions, newQnA]);
      setNewQuestion('');
      setNewAnswer('');
    }
  };

  const data = {
    title: "Frequently Asked Question (FAQ's)",
    rows: questions,
  };

  return (
    <div className="main">
      <div className="faq">
        <Faq data={data} styles={styles} />
        <div>
          <input
            type="text"
            placeholder="Enter the question"
            value={newQuestion}
            onChange={handleQuestionChange}
          />
          <input
            type="text"
            placeholder="Enter the answer"
            value={newAnswer}
            onChange={handleAnswerChange}
          />
          <button onClick={handleAddQuestion}>Add</button>
        </div>
      </div>
    </div>
  );
};

export default Faqs;