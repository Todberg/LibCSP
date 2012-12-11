#define ADDR 9
#define PORT 4
#define PRIO 3

csp_conn_t* conn = csp_connect(PRIO, ADDR, PORT, TIMEOUT, 0);
csp_packet_t* packet = csp_buffer_new(sizeof(csp_packet_t));

sprintf(packet->data, "Hello Server");

packet->length = strlen(“Hello Server”);

csp_send(conn, packet, TIMEOUT_NONE);
csp_close(conn);