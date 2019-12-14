import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import MUIDataTable from "mui-datatables";
import Container from "@material-ui/core/Container";
import EmployeeDesignationCustomToolbar from "./EmployeeDesignationCustomToolbar";
import EmployeeDesignationCustomToolbarSelect from "./EmployeeDesignationCustomToolbarSelect";
import Axios from "axios";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3),
    overflowX: "auto"
  },
  container: {
    marginTop: theme.spacing(3),
    paddingTop: theme.spacing(2),
    paddingBottom: theme.spacing(2)
  }
}));

const divStyle = {
  marginRight: "35px",
  marginLeft: "35px",
  marginTop: "30px"
};

const columns = [
  {
    name: "id",
    label: "Designation Id",
    options: {
      filter: true,
      sort: true,
      display: false
    }
  },
  {
    name: "designationName",
    label: "Employee Designation",
    options: {
      filter: true,
      sort: false
    }
  },
  {
    name: "responsibilities",
    label: "Designation Description",
    options: {
      filter: true,
      sort: false
    }
  }
];

export default function ManageEmployeeDesignation() {
  const classes = useStyles();
  const [designation, setDesignation] = React.useState([]);
  const [showResult, setShowResult] = React.useState("");
  const [message, setMessage] = React.useState("");
  const [trackAdd, setTrackAdd] = React.useState(false);
  const [trackEdit, setTrackEdit] = React.useState(false);
  const [trackDelete, setTrackDelete] = React.useState(false);
  const [values] = React.useState({
    id: ""
  });

  const handleTrackAdd = () => {
    setTrackAdd(!trackAdd);
  };

  const handleTrackEdit = () => {
    setTrackEdit(!trackEdit);
  };

  const handleTrackDelete = () => {
    setTrackDelete(!trackDelete);
  };

  const getId = () => {
    return values.id;
  };

  useEffect(() => {
    Axios.get("http://localhost:1724/api/v1/designation")
      .then(response => {
        console.log(response);
        setDesignation(response.data.results.listAllDesignation);
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Retrive Data");
      });
  }, [trackDelete, trackAdd, trackEdit]);

  const options = {
    filterType: "dropdown",
    selectableRows: "single",
    selectableRowsOnClick: true,
    responsive: "scrollMaxHeight",
    textLabels: {
      body: {
        noMatch:
          designation.length > 0 ? "Loading data..." : "No Records Found!"
      }
    },
    customToolbar: () => {
      return <EmployeeDesignationCustomToolbar onCreate={handleTrackAdd} />;
    },
    customToolbarSelect: () => {
      return (
        <EmployeeDesignationCustomToolbarSelect
          onDelete={handleDelete}
          onEdit={handleTrackEdit}
          id={getId}
        />
      );
    },
    onRowsSelect: allRows => {
      allRows.forEach(row => {
        const dataRow = designation[row.dataIndex];
        values.id = dataRow["id"];
        console.log(values.id);
      });
    }
  };

  const handleDelete = () => {
    Axios.delete(`http://localhost:1724/api/v1/designation/${values.id}`)
      .then(response => {
        console.log(response);
        setShowResult("alert alert-success");
        setMessage(response.data.message);
        handleTrackDelete();
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Delete");
      });
  };

  return (
    <div>
      <div style={divStyle} className={showResult} role="alert">
        {message}
      </div>
      <Container className={classes.container}>
        <MUIDataTable data={designation} columns={columns} options={options} />
      </Container>
    </div>
  );
}
