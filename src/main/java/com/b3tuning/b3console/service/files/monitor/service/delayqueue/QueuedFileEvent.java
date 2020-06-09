/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  QueuedFileEvent is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.delayqueue;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@EqualsAndHashCode(of = {"file"})
public class QueuedFileEvent implements Delayed {

	private final Path               mountPath;
	private final File               file;
	private final WatchEvent.Kind<?> type;
	private final long               delay;
	private final long               origin = System.currentTimeMillis();

	@Override
	public long getDelay(TimeUnit unit) {
		return getDelayAtTime(System.currentTimeMillis(), unit);
	}

	protected long getDelayAtTime(long time, TimeUnit unit) {
		return unit.convert(delay - (time - origin), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed delayed) {
		if (this == delayed) {
			return 0;
		}

		long d;
		if(delayed instanceof QueuedFileEvent) {
			// compare using same time if possible
			long now = System.currentTimeMillis();
			d = getDelayAtTime(now, TimeUnit.MILLISECONDS) - ((QueuedFileEvent)delayed).getDelayAtTime(now, TimeUnit.MILLISECONDS);
		} else {
			d = getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS);
		}
		return ((d == 0) ? 0 : ((d < 0) ? -1 : 1));
	}
}
