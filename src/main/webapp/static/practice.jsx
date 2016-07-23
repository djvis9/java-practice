/*** Begin Valid/Invalid Messages ***/
/* These messages are shown to the user when the enter valid or invalid parentheses. */
var ValidMessage = React.createClass({
    render: function() {
        return (
            <div className="alert alert-success">
                <span>Valid</span>
            </div>
        )
    }
});
var InvalidMessage = React.createClass({
    render: function() {
        return (
            <div className="alert alert-danger">
                <span>Invalid</span>
            </div>
        )
    }
});
/*** End Valid/Invalid Messages ***/



/**
 * ValidateText
 * 
 * React component that lets the user enter text and checks
 * if the text is valid by passing the text to a RESTful endpoint
 * for validation.
 * 
 * props:
 * @title a title shown to the user
 * @description a discription telling the user what is valid text
 * @validationUrl url to call when checking if user entered valid text
 */
var ValidateText = React.createClass({
    getInitialState: function() {
        return {
            isValid: ""
                }
    },
    handleChange: function(e) {
        if(e.target.value != "") {
            // Check if the user entered valid text
            $.get(this.props.validationUrl, {text: e.target.value}, function(response) {
                if(response === true) {
                    this.setState({isValid: <ValidMessage />});
                } else if (response === false) {
                    this.setState({isValid: <InvalidMessage />});
                }
            }.bind(this));
                }
    },
    componentWillMount: function() {
        // use _.debounce so that we wait for the user to stop typing
        // before calling the server to check if the user entered valid
        // text
        this.handleChange = _.debounce(this.handleChange, 200);
    },
    onChange: function(event) {
        this.setState({isValid: ""});
        event.persist();
        this.handleChange(event);
    },
    render: function() {
        return (
            <div>
                <h2>{this.props.title}</h2>
                <p>
                    {this.props.description}
                </p>
                <div>
                    <p>
                        <input type="text" onChange={this.onChange} />
                    </p>
                </div>
                {this.state.isValid}
            </div>
        )
    }
});
    
/**
 * RestQuery
 * 
 * React component that lets the user enter text that
 * will be used to query a REST endpoint for a response.
 * 
 * When the user enters text the passed in formattedOutput
 * function will be called with two arguments: the user input
 * and the respone from the server. The formattedOutput function
 * should return formatted output (eg. React Component) that will
 * be displayed to the user.
 * 
 * @title title to display to user
 * @description description to display to user
 * @url REST endpoint
 * @formattedOutput function that will get called with user input and server response and should return formatted output
 */
var RestQuery = React.createClass({
    getInitialState: function() {
        return {
            output: ""
        }
    },
    handleChange: function(e) {
        var input = e.target.value;
        if(input != "") {
            $.get(this.props.url, {text: input}, function(response) {
                this.setState({output: this.props.formatOutput(input, response)});
            }.bind(this));
        }
    },
    componentWillMount: function() {
        // use _.debounce so that we wait for the user to stop typing
        // before calling the query the server
        this.handleChange = _.debounce(this.handleChange, 200);
    },
    onChange: function(event) {
        this.setState({output: ""});
        event.persist();
        this.handleChange(event);
    },
    render: function() {
        return (
            <div>
                <h2>{this.props.title}</h2>
                <p>
                    {this.props.description}
                </p>
                <div>
                    <p>
                        <input type="text" onChange={this.onChange} />
                    </p>
                </div>
                {this.state.output}
            </div>
        )
    }
});
        
/**
 * Concatenate a list of items together. Useful for concatenating
 * React components with strings.
 * 
 * @input the list of items to concatenate
 */
var Concat = React.createClass({
    render: function() {
        return (
            <span>
                {this.props.input}
            </span>
        )
    }
});

/**
 * React component to print a set.
 * 
 * example output:
 * {x, y, z}
 * 
 * @inputSet a list of items in the set to be printed 
 */
var FormattedSet = React.createClass({
    render: function() {
        return (
            <span>
                {'{'}
                {this.props.inputSet.map(function(element, index) {
                    return index == 0 ? element : <Concat input={[', ', element]} />;
                })}
                {'}'}
            </span>
        )
    }
});
    
/**
 * React component to print a powerset.
 * 
 * example output:
 * 
 * Powerset( {x, y} ): {{}, {x}, {y}, {x, y}}
 * 
 * @inputSet a list representing the input set that the powerset was created from
 * @powerset a list of lists represting a powerset of the input set
 */
var FormattedPowerset = React.createClass({
    render: function() {
        var formattedSets = this.props.powerset.map(function(element) {
            return <FormattedSet inputSet={element} />;
        })
        
        return (
            <div className="alert alert-success">
                <span>Powerset<strong>{'( '}</strong>
                    <FormattedSet inputSet={this.props.inputSet} />
                    <strong>{' )'}</strong> = 
                    <FormattedSet inputSet={formattedSets} />
                </span>
            </div>
        )
    }
});

var getFormattedPowerset = function(input, powerset) {
    return <FormattedPowerset inputSet={input.split("")} powerset={powerset} />;
}

ReactDOM.render(<ValidateText title="Balance Braces" description='Enter some braces (eg. "{[()]}") into the text field to check if they are valid.' validationUrl="braces" />, document.getElementById('bracesApp'));
ReactDOM.render(<ValidateText title="Balance Parentheses" description='Enter some parentheses into the text field to check if they are valid.' validationUrl="parentheses" />, document.getElementById('parenthesesApp'));
ReactDOM.render(<RestQuery title="Powerset" description='Enter a set of charecters (eg. "xyz") to get a powerset.' url="powerset" formatOutput={getFormattedPowerset} />, document.getElementById('powersetApp'));
