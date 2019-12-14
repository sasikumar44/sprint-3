import React,{useEffect} from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import Axios from "axios";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto"
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(3),
    margin: theme.spacing(1),
    width: "230px"
  },
  formControl: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(3),
    margin: theme.spacing(1),
    width: "230px"
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
    width: "120px"
  }
}));
const divStyle = {
  marginRight: "22px",
  marginTop: "10px"
};

export default function AddModuleForm({ onFinish }) {
  const classes = useStyles();
  
  const [values, setValues] = React.useState({
    name: "",
    projectId:""
  });
  const [projects, setProjects] = React.useState([]);
  const [showResult, setShowResult] = React.useState("");
  const [message, setMessage] = React.useState("");

  const handleChange = name => event => {
    setValues({ ...values, [name]: event.target.value });
  };

  const clearValues = () => {
    setValues({
      name: "",
      projectId:""
    });
  };
 
  useEffect(() => {
    Axios.get("http://localhost:1725/api/v1/project")
      .then(response => {
        console.log(response);
        setProjects(response.data.results.List);
      })
      .catch(error => {
        console.log(error);
      });
  },[]);
  
  const handleSubmit = event => {
    event.preventDefault();
    Axios.post(`http://localhost:1725/api/v1/module`, values)
      .then(response => {
        console.log(response);
        setShowResult("alert alert-success");
        setMessage(response.data.message);
        clearValues();
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Save!!");
      });
  };

  return (
    <div>
      <div style={divStyle} className={showResult} role="alert">
        {message}
      </div>
      <form
            className={classes.container}
            autoComplete="off"
            onSubmit={handleSubmit} 
      >
        <Grid container justify="space-between">
          <TextField
            required
            id="module-name"
            label="Module Name"
            className={classes.textField}
            value={values.name}
            onChange={handleChange("name")}
            margin="normal"
            variant="outlined"
          />
          <FormControl required className={classes.formControl}>
            <InputLabel htmlFor="project-name">Project</InputLabel>
            { /*onChange={()=> {handleChange("projectId");test();}*/}
            <Select id="project-name" value={values.projectId} onChange={handleChange("projectId")} >
            {
            projects.map((el,i) => (<MenuItem key = {i} value={el.id}>{el.name}</MenuItem>))
            }
            </Select>
          </FormControl>
        </Grid>
        <Grid container justify="flex-end">
          <Button
            color="primary"
            size="large"
            className={classes.button}
            onClick={onFinish}
          >
            Close
          </Button>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            size="large"
            className={classes.button}
          >
            Add
          </Button>
        </Grid>
      </form>
    </div>
  );
}
