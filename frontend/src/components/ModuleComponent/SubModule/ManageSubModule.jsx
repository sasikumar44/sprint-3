import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import MUIDataTable from "mui-datatables";
import Container from "@material-ui/core/Container";
import SubModuleCustomToolbar from "./SubModuleCustomToolbar";
import SubModuleCustomToolbarSelect from "./SubModuleCustomToolbarSelect";
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
    label: "SubModule Id",
    options: {
      filter: true,
      sort: true,
      display: false
    }
  },
  {
    name: "name",
    label: "SubModule Name",
    options: {
      filter: true,
      sort: false
    }
  },
  {
    name: "moduleName",
    label: "Module Name",
    options: {
      filter: true,
      sort: true
    }
  },
  {
    name: "projectName",
    label: "Project Name",
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

export default function ManageSubModule() {
  const classes = useStyles();
  const [subModule, setSubModule] = React.useState([]);
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
    Axios.get("http://localhost:1725/api/v1/submodule")
      .then(response => {
        console.log(response);
        setSubModule(response.data.results.List);
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
        noMatch: module.length > 0 ? "Loading data..." : "No Records Found!"
      }
    },
    customToolbar: () => {
      return <SubModuleCustomToolbar onCreate={handleTrackAdd} />;
    },
    customToolbarSelect: () => {
      return (
        <SubModuleCustomToolbarSelect
          onDelete={handleDelete}
          onEdit={handleTrackEdit}
          id={getId}
        />
      );
    },
    onRowsSelect: allRows => {
      allRows.forEach(row => {
        const dataRow = subModule[row.dataIndex];
        values.id = dataRow["id"];
        console.log(values.id);
      });
    }
  };

  const handleDelete = () => {
    Axios.delete(`http://localhost:1725/api/v1/submodule/${values.id}`)
      .then(response => {
        console.log(response);
        setShowResult("alert alert-success");
        setMessage(response.data.message);
        handleTrackDelete();
      })
      .catch(error => {
        console.log(error);
        setShowResult("alert alert-danger");
      });
  };

  return (
    <div>
      <div style={divStyle} className={showResult} role="alert">
        {message}
      </div>
      <Container className={classes.container}>
        <MUIDataTable data={subModule} columns={columns} options={options} />
      </Container>
    </div>
  );
}
