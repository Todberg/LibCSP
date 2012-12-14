typedef union {
  uint32_t ext;
  struct __attribute__((__packed__)) {
    unsigned int pri : CSP_ID_PRIO_SIZE;
    unsigned int src : CSP_ID_HOST_SIZE;
    unsigned int dst : CSP_ID_HOST_SIZE;
    unsigned int dport : CSP_ID_PORT_SIZE;
    unsigned int sport : CSP_ID_PORT_SIZE;
    unsigned int flags : CSP_ID_FLAGS_SIZE;
  };
} csp_id_t;
