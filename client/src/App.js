import './App.css';
import QuotePage from "./QuotePage";
import PersonPage from "./PersonPage";
import { Routes, Route } from 'react-router-dom';
import { Link } from 'react-router-dom';

function App() {
  return (
      <div className="App">
          <h1>Spring Boot React</h1>
          <hr/>
          <p><Link to="/person">Person API</Link></p>
          <p><Link to="/quote">Quote API</Link></p>
          <Routes>
              <Route path="/person" element={<PersonPage />} />
              <Route path="/quote" element={<QuotePage />} />
          </Routes>
      </div>
  );
}

export default App;
