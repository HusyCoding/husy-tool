package com.husy.tool.core.lang;

import java.util.Arrays;

/**
 * @Description: Top Ten Classic Array Sorting Algorithm Util
 * @author: husy
 * @date 2020/1/13
 */
public class ArraySortUtils {
	/**
	 * bubbleSort sorting them ascending
	 *
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		if (isNull(array)) {
			return;
		}
		// 表示趟数，一共arr.length-1次。
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = array.length - 1; j > i; j--) {
				// 相邻元素对比，交换
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	/**
	 * selectionSort is sorting them ascending
	 *
	 * @param array
	 */
	public static void selectionSort(int[] array) {
		if (isNull(array)) {
			return;
		}
		int lenth = array.length;
		int minIndex;
		for (int i = 0; i < lenth - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < lenth; j++) {
				// 寻找最小的数
				if (array[j] < array[minIndex]) {
					// 将最小数的索引保存
					minIndex = j;
				}
			}
			if (minIndex != i) {
				swap(array, i, minIndex);
			}
		}
	}

	/**
	 * Sort arrays by inserting
	 *
	 * @param array
	 */
	public static void insertionSort(int[] array) {
		if (isNull(array)) {
			return;
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j - 1, j);
				} else { // 不需要交换
					break;
				}
			}
		}
	}

	/**
	 * Sort arrays by shell
	 *
	 * @param array
	 */
	public static void shellSort(int[] array) {
		if (isNull(array)) {
			return;
		}
		// 间隔或增量
		int incre = array.length;

		while (incre > 1) {
			// 2 相当于每个子序列的个数，首次这里以 length/2 为间隔分组，可以得到 incre 组序列
			incre = incre / 2;
			// 根据增量分为若干子序列
			for (int k = 0; k < incre; k++) {
				// 每组序列进行 插入排序
				// 注意，遍历时每组序列中相邻两个元素之间步长为 incre
				for (int i = k + incre; i < array.length; i += incre) {
					for (int j = i; j > k; j -= incre) {
						if (array[j] < array[j - incre]) {
							swap(array, j - incre, j);
						} else {
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Sort arrays by merging
	 *
	 * @param array
	 * @return
	 */
	public static int[] mergeSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int num = array.length / 2;
		int[] left = Arrays.copyOfRange(array, 0, num);
		int[] right = Arrays.copyOfRange(array, num, array.length);

		return mergeTwoArray(mergeSort(left), mergeSort(right));
	}

	/**
	 * Merge two arrays and follow them in ascending order
	 *
	 * @param leftArr
	 * @param rightArr
	 * @return
	 */
	public static int[] mergeTwoArray(int[] leftArr, int[] rightArr) {
		int i = 0, j = 0, k = 0;
		// 申请额外空间保存归并之后数据
		int[] result = new int[leftArr.length + rightArr.length];
		// 选取两个序列中的较小值放入新数组
		while (i < leftArr.length && j < rightArr.length) {
			if (leftArr[i] <= rightArr[j]) {
				result[k++] = leftArr[i++];
			} else {
				result[k++] = rightArr[j++];
			}
		}
		// 序列a中多余的元素移入新数组
		while (i < leftArr.length) {
			result[k++] = leftArr[i++];
		}
		// 序列b中多余的元素移入新数组
		while (j < rightArr.length) {
			result[k++] = rightArr[j++];
		}
		return result;
	}

	/**
	 * 快速排序
	 *
	 * @param array
	 */
	public static void quickSort(int array[]) {
		if (isNull(array)) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int array[], int low, int high) {
		if (low >= high) {
			return;
		}

		int left = low;
		int right = high;
		// 基准的值
		int temp = array[left];

		while (left < right) {
			// 从后向前，找到比base基准值小的值
			while (left < right && array[right] >= temp) {
				right--;
			}
			// 从后向前找到比基准小的元素，先进行交换的第2步，因为array[left] 已经备份到temp 了
			array[left] = array[right];

			// 从前往后，找到比base基准值大的值
			while (left < right && array[left] <= temp) {
				left++;
			}
			// 交换这两个数,进行交换的第3步
			array[right] = array[left];
		}
		// 基准值填补到中间位置，准备分治递归快排
		array[left] = temp;
		quickSort(array, low, left - 1);
		quickSort(array, left + 1, high);
	}

	public static void heapSort(int[] array) {
		if (isNull(array)) {
			return;
		}
		// 1.构建大顶堆
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			// 从第一个非叶子结点从下至上，从右至左调整结构
			adjustHeap(array, i, array.length);
		}
		// 2.调整堆结构+交换堆顶元素与末尾元素
		for (int j = array.length - 1; j > 0; j--) {
			// 将堆顶元素与末尾元素进行交换
			swap(array, 0, j);
			// 重新对堆进行调整
			adjustHeap(array, 0, j);
		}

	}

	private static void adjustHeap(int[] arr, int i, int length) {
		// 先取出当前元素i
		int temp = arr[i];
		// 从i结点的左子结点开始，也就是2i+1处开始
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			// 如果左子结点小于右子结点，k指向右子结点
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}
			// 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
			if (arr[k] > temp) {
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}
		// 将temp值放到最终的位置
		arr[i] = temp;
	}

	public static void countingSort(int[] arr) {
		if (isNull(arr)) {
			return;
		}
		int max = arr[0], min = arr[0];
		// 计算最大最小值
		for (int i : arr) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		// Java 8 新写法
		// max = Arrays.stream(arr).max().getAsInt();
		// min = Arrays.stream(arr).min().getAsInt();

		int length = arr.length;
		// 最大最小元素之间范围[min, max]的长度
		int range = max - min + 1;

		// 1. 计算频率，在需要的数组长度上额外 +1
		int[] count = new int[range + 1];
		for (int i = 0; i < length; i++) {
			// 使用加1后的索引，有重复的该位置就自增
			count[arr[i] - min + 1]++;
		}

		// 2. 频率 -> 元素的开始索引
		for (int i = 0; i < range; i++) {
			count[i + 1] += count[i];
		}

		// 3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
		int[] aux = new int[length];
		for (int i = 0; i < length; i++) {
			// 填充一个数据后，自增，以便相同的数据可以填到下一个空位
			aux[count[arr[i] - min]++] = arr[i];
		}
		// 4. 数据回写
		for (int i = 0; i < length; i++) {
			arr[i] = aux[i];
		}
	}

	/**
	 * 桶排序
	 *
	 * @param arr
	 */
	public static void bucketSort(int[] arr) {
		if (isNull(arr)) {
			return;
		}

		int minValue = arr[0];
		int maxValue = arr[0];
		int bucketSize = arr.length;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < minValue) {
				// 输入数据的最小值
				minValue = arr[i];
			} else if (arr[i] > maxValue) {
				// 输入数据的最大值
				maxValue = arr[i];
			}
		}
		// Java 8 新写法
		// max = Arrays.stream(arr).max().getAsInt();
		// min = Arrays.stream(arr).min().getAsInt();

		int bucketCount = (int) (Math.floor((maxValue - minValue) / bucketSize) + 1);
		int[][] buckets = new int[bucketCount][0];

		// 利用映射函数将数据分配到各个桶中
		for (int i = 0; i < arr.length; i++) {
			// 获取映射索引
			int index = (int) Math.floor((arr[i] - minValue) / bucketSize);

			// 自动扩容，并保存数据
			int[] bucket = buckets[index];
			bucket = Arrays.copyOf(bucket, bucket.length + 1);
			// a[i] 放入桶的最后面
			bucket[bucket.length - 1] = arr[i];
			buckets[index] = bucket;
		}

		int arrIndex = 0;
		for (int[] bucket : buckets) {
			if (bucket.length <= 0) {
				continue;
			}
			// 对每个桶进行排序，这里使用了插入排序
			insertionSort(bucket);
			for (int value : bucket) {
				arr[arrIndex++] = value;
			}
		}
	}

	/**
	 * 基数排序
	 *
	 * @param arr
	 */
	public static void radixSort(int[] arr) {
		if (isNull(arr)) {
			return;
		}
		// 获取最大数值
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}

		// 获取最高位数
		int maxDigit = 0;
		for (long temp = maxValue; temp != 0; temp /= 10) {
			maxDigit++;
		}

		int mod = 10;
		int dev = 1;
		// 当前位置值
		int digit = 1;
		while (digit <= maxDigit) {
			// 数组的第一维表示可能的余数0-9
			// 如果要考虑负数的情况，这里可以将队列数扩展一倍，其中 [0-9]对应负数，[10-19]对应正数
			int[][] counter = new int[10][0];
			for (int i = 0; i < arr.length; i++) {
				// 如果考虑了负数，还需要 +10
				int index = (arr[i] % mod) / dev;
				// 自动扩容，并保存数据
				int[] bucket = counter[index];
				bucket = Arrays.copyOf(bucket, bucket.length + 1);
				// a[i] 放入桶的最后面
				bucket[bucket.length - 1] = arr[i];
				counter[index] = bucket;
			}
			// 重新对按照统一位数值大小排序组
			int pos = 0;
			for (int[] bucket : counter) {
				for (int value : bucket) {
					arr[pos++] = value;
				}
			}
			mod *= 10;
			dev *= 10;
			digit++;
		}
	}

	/**
	 * if array is null or length Less than 1
	 *
	 * @param arr
	 * @return
	 */
	private static boolean isNull(int[] arr) {
		if (arr == null || arr.length < 1) {
			return true;
		}
		return false;
	}

	/**
	 * exchange left value and right value at array index
	 *
	 * @param array
	 * @param left
	 * @param right
	 */
	private static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	public static void main(String[] args) {
		int[] arr = {49, 38, 652, 97, 76, 113, 27, 49, 78, 34, 12, 64, 1, 1561};
		// int[] arr = { 9, 4, 1, 3, 5, 2, 8, 13, 6 };
		System.out.println("排序之前：" + Arrays.toString(arr));
		radixSort(arr);
		System.out.println("排序之前：" + Arrays.toString(arr));
	}

}


