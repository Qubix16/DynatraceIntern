import { useEffect, useState } from "react";
import CompType from './CompType';
import ErrorType from "./ErrorType";
const codeRegex = /^[a-z]{3}$/;

const SimpleCountComp = (props) => {
    const title = props.title;
    const desc = props.desc;
    const type = props.type;

    const [url, setUrl] = useState("");
    const [data, setData] = useState(null);
    const [topCount, setTopCount] = useState(0);
    const [code, setCode] = useState("");
    const [isCodeValid, setIsCodeValid] = useState(false);
    const [isTopCountValid, setIsTopCountValid] = useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();

        fetch('http://localhost:8080/api/'+ url +'/'+ code + '/' + topCount,{
            headers: {
                Accept: 'application/json'
            }
        })
        .then(response => 
            {
                if(response.ok)
                {
                    setError(null);
                    return response.json();
                }
                else if(response.status === 404)
                {
                    setData(null);
                    return setError(ErrorType.NOT_FOUND_TOPCOUNT);
                }
                else
                {
                    setData(null);
                    return setError(ErrorType.OTHER);
                }

            })
        .then(data => setData(data))
        .catch(error => console.error(error));
    }

    useEffect(() => {
       if(codeRegex.test(code))
            setIsCodeValid(true);
       else
            setIsCodeValid(false); 
    }, [code])

    useEffect(() => {
        if(topCount > 0 && topCount<= 255)
             setIsTopCountValid(true);
        else
             setIsTopCountValid(false); 
     }, [topCount])

    useEffect(()=>{
        if(type === CompType.MIN_MAX)
            setUrl("min_max");
        else
            setUrl("major_diff");
    },[])

    function showResult(type, data)
    {
        if(data !== null )
            if(type === CompType.MIN_MAX)
                return <span>Min: {data?.min} Max: {data?.max}</span>
            else if(type === CompType.MAJOR_DIFF)
                return <span>Value: {data}</span>
            else return <span>Component Type Error</span>
        else return <span>-</span>
    }

    return ( 
    <div className="main">
        <div className="main_left_section">
        <div className="main_left_section_title">
            <h2>{title}:</h2>
        </div>
        <div className="main_left_section_title">
            <span>{desc}</span>
        </div>
        <div className="main_left_section_input_box">
                <div className="main_left_section_input_box_inputs">
                    <div className="main_left_section_input_box_input">
                        <input              
                            type="int"  
                            placeholder="Top Count"
                            onChange={(e) => setTopCount(e.target.value)}
                            onBlur={(e) => setTopCount(e.target.value)}
                        />
                        <div className="main_left_section_input_box_info">
                            {!isTopCountValid && "Top Count is not valid!"}
                            {isTopCountValid && "Top Count is valid!"}
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
                <button type="submit" onClick={handleSubmit} disabled={!(isCodeValid && isTopCountValid)}>
                    Get
                </button>
            </div>
        </div>
        <div className="main_right_section">
            <div className="main_right_section_text">
                Result:
                <div className="main_right_section_text_error">{error}</div>
            </div>
            <div className="main_right_section_result">
                {showResult(type, data)}
            </div>
        </div>
        </div>
    </div>
    );
}
 
export default SimpleCountComp;