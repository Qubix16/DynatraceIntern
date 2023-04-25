import { useEffect, useState } from "react";
import ErrorType from "./ErrorType";

const dateRegex = /^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/;
const codeRegex = /^[a-z]{3}$/;

const SimpleDateComp = (props) => {
    const title = props.title;
    const desc = props.desc;

    const [value,setValue] = useState(0.0);
    const [date,setDate] = useState(null);
    const [code,setCode] = useState("");
    const [isCodeValid, setIsCodeValid] = useState(false);
    const [isDateValid, setIsDateValid] = useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        
        fetch('http://localhost:8080/api/average_exchange/'+ code +'/'+ date,{
            headers: {
                Accept: 'application/json'
            }
        })
        .then(response => 
            {
                if(response.ok)
                {
                    setError("");
                    return response.json();
                }
                else if(response.status === 404)
                {
                    return setError(ErrorType.NOT_FOUND_DATE);
                }
                else
                {
                    return setError(ErrorType.OTHER);
                }

            })
        .then(data => setValue(data))
        .catch(error => console.log(error))
        
    }

    useEffect(() => {
       if(codeRegex.test(code))
            setIsCodeValid(true);
       else
            setIsCodeValid(false); 
    }, [code])

    useEffect(() => {
        if(dateRegex.test(date))
             setIsDateValid(true);
        else
             setIsDateValid(false); 
     }, [date])

    return ( 
    <div className="main">
        <div className="main_left_section">
            <div className="main_left_section_title">
                <h2>{title}:</h2>
            </div>
            <div className="main_left_section_desc">
                <span>{desc}</span>
            </div>
            <div className="main_left_section_input_box">
                <div className="main_left_section_input_box_inputs">
                    <div className="main_left_section_input_box_input">
                        <input              
                            type="text"  
                            placeholder="Date"
                            onChange={(e) => setDate(e.target.value)}
                            onBlur={(e) => setDate(e.target.value)}
                        />
                        <div className="main_left_section_input_box_info">
                            {!isDateValid && "Date is not valid!"}
                            {isDateValid && "Date is valid!"}
                        </div>
                    </div>
                    <div className="main_left_section_input_box_input">
                        <input              
                            type="text"  
                            placeholder="Code"
                            onChange={(e) => setCode(e.target.value)}
                            onBlur={(e) => setCode(e.target.value)}
                        />
                        <div className="main_left_section_input_box_info">
                            {!isCodeValid && "Code is not valid!"}
                            {isCodeValid && "Code is valid!"}
                        </div>
                    </div>
                </div>
                <div className="main_left_section_button">
                    <button type="submit" onClick={handleSubmit} disabled={!(isCodeValid && isDateValid)}>
                        Get
                    </button>
                </div>
            </div>
        </div>
        <div className="main_right_section">
            <div className="main_right_section_text">
                Result:
                <div className="main_right_section_text_error">{error}</div>
            </div> 
            <div className="main_right_section_result">
                {value}
            </div>
        </div>
    </div>
    );
}
 
export default SimpleDateComp;