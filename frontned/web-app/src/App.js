import './App.css';
import SimpleDateComp from './SimpleDateComp';
import SimpleCountComp from './SimpleCountComp';
import CompType from './CompType';

const TITLE1 = "Average exchange rate"
const DESC1 = "Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), provide its average exchange rate."

const TITLE2 = "Max and min average value"
const DESC2 = "Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average)."

const TITLE3 = "Major difference"
const DESC3 = "Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates)."



function App() {
  return (
    <div className="App">
      <SimpleDateComp title={TITLE1} desc={DESC1}/>
      <SimpleCountComp title={TITLE2} desc={DESC2} type={CompType.MIN_MAX} />
      <SimpleCountComp title={TITLE3} desc={DESC3} type={CompType.MAJOR_DIFF}/>
    </div>
  );
}

export default App;
