import { useState } from "react";
import { Link } from "react-router-dom";
import { Button, ButtonGroup, Table } from "reactstrap";
import tokenService from "../../../util/services/token.service";
import "./../admin.css";
import deleteFromList from "../../../util/deleteFromList";
import getErrorModal from "../../../util/getErrorModal";
import useFetchState from "../../../util/useFetchState";

const jwt = tokenService.getLocalAccessToken();

export default function UserListAdmin() {
  const [message, setMessage] = useState(null);
  const [visible, setVisible] = useState(false);
  const [users, setUsers] = useFetchState(
    [],
    `/api/v1/users`,
    jwt,
    setMessage,
    setVisible
  );
  const [players, setPlayers] = useFetchState(
    [],
    `/api/v1/player`,
    jwt,
    setMessage,
    setVisible
  );
  const [alerts, setAlerts] = useState([]);

  const safeDeletePlayer = (userId) => {
    const playerUser = players.find((player) => player.user.id === userId); // Find the player associated with the user
    if (!playerUser) {
      deleteFromList(
        `/api/v1/users/${userId}`,
        userId,
        [users, setUsers],
        [alerts, setAlerts],
        setMessage,
        setVisible
      );
    } else {
      deleteFromList(
        `/api/v1/player/${playerUser.id}`,
        playerUser.id,
        [players, setPlayers],
        [alerts, setAlerts],
        setMessage,
        setVisible
      );
    }
  };

  const userList = users.map((user) => {
    return (
      <tr key={user.id}>
        <td>{user.username}</td>
        <td>{user.authority.authority}</td>
        <td>
          <ButtonGroup>
            <Button
              size="sm"
              color="primary"
              aria-label={"edit-" + user.id}
              tag={Link}
              to={"/controlPanel/users/" + user.id}
            >
              Edit
            </Button>
            <Button
              size="sm"
              color="danger"
              aria-label={"delete-" + user.id}
              onClick={() => safeDeletePlayer(user.id)}
            >
              Delete
            </Button>
          </ButtonGroup>
        </td>
      </tr>
    );
  });

  const modal = getErrorModal(setVisible, visible, message);

  return (
    <div className="admin-page-container">
      <h1 className="text-center">Users</h1>
      {alerts.map((a) => a.alert)}
      {modal}
      <Button color="success" tag={Link} to="/controlPanel/users/new">
        Add User
      </Button>
      <div>
        <Table aria-label="users" className="mt-4">
          <thead>
            <tr>
              <th>Username</th>
              <th>Authority</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>{userList}</tbody>
        </Table>
        <Button color="success" tag={Link} to="/controlPanel">
          Back to Control Panel
        </Button>
      </div>
    </div>
  );
}
