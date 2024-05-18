package studentmanagement;

class Student{
	private String name;
	private String id;
	private String marks[][];
	@SuppressWarnings("unused")
	private int totalMarks = 0;
	@SuppressWarnings("unused")
	private double averageMark = 0.0;
	
	public Student(String name, String id, String marks[][]) {
	    this.name = name;
	    this.id = id;
	    this.marks = marks;
	}
	
	public String getAStudent() {
	    return (this.name + " | " + this.id + " | " + "Mathematics: " + this.marks[0][1] + " | Science:  " + this.marks[1][1]);
	}
	
	public int getAStudentTotal() {
	    this.totalMarks = Integer.valueOf(this.marks[0][1]) + Integer.valueOf(this.marks[1][1]);
	    return (Integer.valueOf(this.marks[0][1]) + Integer.valueOf(this.marks[1][1]));
	}
	
	public double getAStudentAverage() {
	    this.averageMark = (Double.parseDouble(this.marks[0][1]) + Double.parseDouble(this.marks[1][1])) / 2;
	    return ((Double.parseDouble(this.marks[0][1]) + Double.parseDouble(this.marks[1][1])) / 2);
	}

	public String getStudentName() {
		return this.name;
	}
	
	public String getStudentID() {
	    return this.id;
	}
	
	public boolean setName(String name) {
	    this.name = name;
	    return true;
	}

	public String getSubjectMark(int subjectCode) {
		return this.marks[subjectCode][1];
	}
	
	public boolean setMark(String subject, String mark) {
	    for(int i = 0; i < this.marks.length; i++) {
	        if(this.marks[i][0].equals(subject)) {
	            this.marks[i][1] = mark;
	            return true;
	        }
	    }
	    
	    return false;
	}
}