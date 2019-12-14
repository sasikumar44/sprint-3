import React, {useEffect} from "react";
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

export default function EditSubModuleForm({ id, onFinish }) {
  const classes = useStyles();
  const [values, setValues] = React.useState({
    name: "",
    projectId:"",
    moduleId:"",
    description:""
  });
  const [modules, setModules] = React.useState([]);
  const [showResult, setShowResult] = React.useState("");
  const [message, setMessage] = React.useState("");

  useEffect(() => {
    Axios.get(`http://localhost:1725/api/v1/submodule/${id()}`)
      .then(response => {
        console.log(response);
        let result = response.data.results.Object;
        updateData(result);
        
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Retrive Data!!");
      });
  }, [id]);

  useEffect(() => {
    Axios.get("http://localhost:1725/api/v1/module")
      .then(response => {
        console.log(response);
        setModules(response.data.results.List);
      })
      .catch(error => {
        console.log(error);
      });
  },);

  const updateData = data => {
    setValues({
      id: data.id,
      name: data.name,
      moduleId: data.moduleId
    });
  };

  const handleChange = name => event => {
    setValues({ ...values, [name]: event.target.value });
  };

  const handleSubmit = event => {
    event.preventDefault();
    Axios.put(`http://localhost:1725/api/v1/submodule`, values)
      .then(response => {
        console.log(response);
        setShowResult("alert alert-success");
        setMessage(response.data.message);
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Update!!");
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
        onSubmit={handleSubmit}>
        <Grid container justify="space-between">
          <FormControl required className={classes.formControl}>
            <InputLabel  htmlFor="submodule-name">
              Module Name
            </InputLabel>
            <Select id="module-name" value={values.moduleId} onChange={handleChange("moduleId")} >
            {
            modules.map((el,i) => (<MenuItem key = {i} value={el.id}>{el.name}</MenuItem>))
            }
             
            </Select>
          </FormControl>
          <TextField
            required
            id="submodule-name"
            label="Sub Module Name"
            value={values.name}
            onChange={handleChange("name")}
            className={classes.textField}
            margin="normal"
            variant="outlined"
          />
        </Grid>
        <Grid container justify="flex-end">
          <Button             
            color="primary"
            size="large"
            className={classes.button}
            onClick={onFinish}>
            Close
          </Button>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            size="large"
            className={classes.button}
          >
            Update
          </Button>
        </Grid>
      </form>
    </div>
  );
}
