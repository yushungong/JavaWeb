package net.com.gong.thread.my_synchronization;

public class Test {

	public static void main(String[] args) {
		/*SleepyBounedeBuffer<Object> buffer = new SleepyBounedeBuffer<>(5);
		for (int i = 0; i < 6; i++) {
			try {
				buffer.put(new Object());
				buffer.take();
				System.out.println(i);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		final BoundedBuffer buffer = new BoundedBuffer(5);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("试图获取一个数据");
				Object obj = null;
				try {
					obj = buffer.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(obj);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					buffer.put("数据1");;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("放入一个数据");
			}
		});
		t1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}

}
