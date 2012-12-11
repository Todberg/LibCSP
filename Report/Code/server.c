#define PORT 7
#define CONN_QUEUE_SIZE 32

 csp_conn_t* conn;
 csp_packet_t* packet;
 csp_socket_t* socket = csp_socket(0);
 
 csp_bind(socket, PORT);
 csp_listen(socket, CONN_QUEUE_SIZE);

 while(true) {
   conn = csp_accept(socket, 1000);
   packet = csp_read(conn, 100);

   printf(“%s\r\n”, packet->data);

   csp_buffer_free(packet);
   csp_close(conn);
 }