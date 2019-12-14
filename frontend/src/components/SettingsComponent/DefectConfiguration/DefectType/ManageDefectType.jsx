import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import MUIDataTable from "mui-datatables";
import Container from "@material-ui/core/Container";
import DefectTypeCustomToolbar from "./DefectTypeCustomToolbar";
import DefectTypeCustomToolbarSelect from "./DefectTypeCustomToolbarSelect";
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

const columns = [
  {
    name: "id",
    label: "Id",
    options: {
      filter: true,
      sort: true,
      display: false
    }
  },
  {
    name: "name",
    label: "Name",
    options: {
      filter: true,
      sort: false
    }
  },
  {
    name: "description",
    label: "Description",
    options: {
      filter: true,
      sort: false
    }
  }
];

const divStyle = {
  marginRight: "35px",
  marginLeft: "35px",
  marginTop: "30px"
};

export default function ManageDefectType() {
  const classes = useStyles();
  const [type, setType] = React.useState([]);
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
    Axios.get("http://localhost:8087/api/v1/type")
      .then(response => {
        console.log(response);
        setType(response.data.results.listAllType);
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
        setMessage("Failed to Retrive Data");
      });
  }, [trackDelete, trackAdd, trackEdit]);

  const options = {
    filterType: "checkbox",
    selectableRows: "single",
    selectableRowsOnClick: true,
    responsive: "scrollMaxHeight",
    textLabels: {
      body: {
        noMatch: type.length > 0 ? "Loading data..." : "No Records Found!"
      }
    },
    customToolbar: () => {
      return <DefectTypeCustomToolbar onCreate={handleTrackAdd} />;
    },
    customToolbarSelect: () => {
      return (
        <DefectTypeCustomToolbarSelect
          onDelete={handleDelete}
          onEdit={handleTrackEdit}
          id={getId}
        />
      );
    },
    onRowsSelect: allRows => {
      allRows.forEach(row => {
        const dataRow = type[row.dataIndex];
        values.id = dataRow["id"];
        console.log(values.id);
      });
    }
  };

  const handleDelete = () => {
    Axios.delete(`http://localhost:8087/api/v1/type/${values.id}`)
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
        <MUIDataTable data={type} columns={columns} options={options} />
      </Container>
    </div>
  );
}
