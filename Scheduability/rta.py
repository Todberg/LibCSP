import math

tasks = []

class Task(object):
	def name(self):
		return self.name
		
	def name(self, name):
		self.name = name

	def period(self):
		return self.period

	def period(self, period):
		self.period = period

	def computation_time(self):
		return self.computation_time

	def computation_time(self, computation_time):
		self.computation_time = computation_time

	def priority(self):
		return self.priority

	def priority(self, priority):
		self.priority = priority

	def response_time(self):
		return self.response_time

	def response_time(self, response_time):
		self.response_time = response_time

	def deadline(self):
		return self.deadline

	def deadline(self, deadline):
		self.deadline = deadline

def init_tasks():
	t1 = Task()
	t1.name = "Pinger"
	t1.period = 500
	t1.deadline = 200
	t1.computation_time = 150
	t1.priority = 15

	t2 = Task()
	t2.name = "Checker"
	t2.period = 500
	t2.deadline = 300
	t2.computation_time = 20
	t2.priority = 10

	t3 = Task()
	t3.name = "Recovery"
	t3.period = 500
	t3.deadline = 350
	t3.computation_time = 40
	t3.priority = 5

	global tasks
	tasks = [t1, t2, t3]

def calc_w_next(t, w_prev):
	w_next = t.computation_time + sum([math.ceil(float(w_prev)/task.period) * task.computation_time for task in tasks if task.priority > t.priority])
	return w_next

def calc_response_times():
	for task in tasks:
		w_prev = task.computation_time
		while True:
			w_next = calc_w_next(task, w_prev)
			if w_next == w_prev: # Value found
				task.response_time = w_prev
				break
			if w_next > task.deadline: # Value not found
				print "value not found"
				break
			w_prev = w_next

def check_response_times():
	status = True
	for task in tasks:
		if task.response_time > task.period:
			status = False
	if status:
		print "Tasks can be scheduled :)"
	else:
		print "Tasks can not be scheduled :("

def print_schedule():
	print "----------------------------"
	for task in tasks:
		print "N:  " + task.name
		print "T:  " + str(task.period)
		print "D:  " + str(task.deadline)
		print "C:  " + str(task.computation_time)
		print "P:  " + str(task.priority)
		print "R:  " + str(task.response_time)
		print "----------------------------"

def main():
	init_tasks()
	calc_response_times()
	check_response_times()
	print_schedule()

# Go!
main()
